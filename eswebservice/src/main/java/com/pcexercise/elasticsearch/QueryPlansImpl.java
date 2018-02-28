package com.pcexercise.elasticsearch;

import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class QueryPlansImpl implements QueryPlans {

    private RestHighLevelClient client;
    private static String PLAN_INDEX = "esbaplan";
    private static String PLAN_INDEX_TYPE = "plan";
    private static int DEFAULT_TIME_OUT = 120;
    private static long TIME_TO_KEEP_SCROLL = 2L;
    private static int MAX_RECORDS = 10;

    /**
     * Uses the Elasticsearh high level client to make some queries.
     * @param client The elastic search client.
     */
    public QueryPlansImpl(RestHighLevelClient client) {
        this.client = client;
    }

    /**
     * Allows searching the plans by a search string on a specified search field.
     *
     * @param searchString The plan search string
     * @param searchField The plan search field
     * @return The response containing partial records. Use the scroll API to retrieve the next batch.
     * @throws IOException if there was a problem communicating with the service.
     */
    public  SearchResponse searchPlans(String searchField, String searchString) throws IOException {
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(TIME_TO_KEEP_SCROLL));
        SearchRequest searchRequest = new SearchRequest(PLAN_INDEX);
        searchRequest.types(PLAN_INDEX_TYPE);
        searchRequest.scroll(scroll);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery(searchField, searchString));
        sourceBuilder.from(0);
        sourceBuilder.size(MAX_RECORDS);
        sourceBuilder.timeout(new TimeValue(DEFAULT_TIME_OUT, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest);
        return response;
    }

    @Override
    public SearchResponse scrollPlans(String scrollId) throws IOException {
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(DEFAULT_TIME_OUT));
        SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
        scrollRequest.scroll(scroll);
        SearchResponse response = client.searchScroll(scrollRequest);
        return response;
    }
}
