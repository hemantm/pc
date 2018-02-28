package com.pcexercise.aws;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;

import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import com.pcexercise.elasticsearch.ElasticSearchClient;
import com.pcexercise.elasticsearch.QueryPlans;
import com.pcexercise.elasticsearch.QueryPlansImpl;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 * This handler class handles the requests to the Elasticsearch
 *
 */
public class QueryPlanHandler implements RequestStreamHandler {

    public static final String STATUS_CODE = "statusCode";
    public static final String HEADERS = "headers";
    public static final String BODY = "body";
    public static final String EXCEPTION = "exception";
    public static final String IS_BASE_64_ENCODED = "isBase64Encoded";
    public static final String HTTP_BAD_REQUEST = "400";
    public static final String HTTP_SERVICE_UNAVAILABLE = "503";
    public static final String HTTP_SUCCESS = "200";
    public static final String TOTAL_HITS = "totalHits";
    public static final String JSON_HITS = "hits";
    public static final String JSON_COUNT = "count";
    public static final String JSON_SCROLL_ID = "scrollId";

    private LambdaLogger logger = null;

    /**
     * Handles the API requests from API Gateway
     * @param inputStream The input stream from the API Gateway.
     * @param outputStream The output stream back to the API gateway.
     * @param context The context from the API gateway.
     * @throws IOException thrown if the write back to the API gateway fails.
     */
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

        logger = context.getLogger();
        logger.log("Loading Java Lambda handler of ProxyWithStream");

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONObject responseJson = new JSONObject();
        String responseCode = HTTP_SUCCESS;

        try {
            JSONParser parser = new JSONParser();
            JSONObject event = (JSONObject)parser.parse(reader);
            QueryPlanParams params = new QueryPlanParams(event);
            logger.log("Initializing Elasticsearch client...");
            ElasticSearchClient esclient = ElasticSearchClient.getInstance();
            QueryPlans plans = new QueryPlansImpl(esclient.getRestClient());
            JSONObject responseBody = search(params, plans);

            JSONObject headerJson = new JSONObject();
            responseJson.put(IS_BASE_64_ENCODED, false);
            responseJson.put(STATUS_CODE, responseCode);
            responseJson.put(HEADERS, headerJson);
            responseJson.put(BODY, responseBody.toString());

        } catch(ParseException pex) {
            //If there was a parse exception it is most likely the user error.
            responseJson.put(STATUS_CODE, HTTP_BAD_REQUEST);
            responseJson.put(EXCEPTION, pex);
        } catch(IOException pex) {
            //This is a result of the unavailability of the Elasticsearch service and therefore we will return service
            //unavailable. The client can try again.
            responseJson.put(STATUS_CODE, HTTP_SERVICE_UNAVAILABLE);
            responseJson.put(EXCEPTION, pex);
        }

        logger.log(responseJson.toJSONString());
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseJson.toJSONString());
        writer.close();
    }

    /**
     * The search query that returns the response body with hits and other information.
     *
     * @param params The query parameters that came over the request.
     * @param plans The query plan interface.
     * @return the body json object.
     * @throws IOException due to elasticsearch service unavailability.
     * @throws ParseException due to host port parsing that can happen due to host port invalid number.
     */
    public JSONObject search(QueryPlanParams params, QueryPlans plans) throws IOException, ParseException {
        SearchResponse response = null;
        if (params.isScrollQuery() ) {
            logger.log("Searching elastic search for scrollId=" + params.getScrollId());
            response = plans.scrollPlans(params.getScrollId());
        } else {
            logger.log("Searching elastic search for field=" + params.getFieldName() + " value=" + params.getSearchString());
            response = plans.searchPlans(params.getFieldName(), params.getSearchString());
        }
        logger.log("Elastic search responded...");
        return getResponseJson(response);
    }

    /**
     * Gets a JSON response object from the search response object.
     * @param response The JSON response object.
     * @return The JSON object containing the final response.
     * @throws ParseException the parse exception.
     */
    public JSONObject getResponseJson(SearchResponse response) throws ParseException {
        JSONObject responseJson = new JSONObject();
        long totalHits = response.getHits().totalHits;
        responseJson.put(TOTAL_HITS, totalHits);

        SearchHits hits = response.getHits();
        long count = hits.getHits().length;
        JSONArray hitJsonList = new JSONArray();
        if (hits != null) {
            JSONParser parser = new JSONParser();
            for (SearchHit hit : hits.getHits()) {
                JSONObject hitObj = (JSONObject)parser.parse(hit.getSourceAsString());
                hitJsonList.add(hitObj);
            }
        }
        responseJson.put(JSON_HITS, hitJsonList);
        responseJson.put(JSON_COUNT, count);
        //If the count of records is less than the total hits then scrolling is supported and we return scrollId
        //otherwise we are done and we do not return the scrollId.
        if (count < totalHits) {
            responseJson.put(JSON_SCROLL_ID, response.getScrollId());
        }
        return responseJson;
    }
}
