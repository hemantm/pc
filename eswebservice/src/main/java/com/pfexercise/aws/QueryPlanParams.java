package com.pfexercise.aws;

import org.json.simple.JSONObject;

/**
 * This class simply wraps the query parameters from the lambda service
 */
public class QueryPlanParams {

    public static String PLAN_NAME_FIELD = "PLAN_NAME";
    public static String SPONSOR_NAME_FIELD = "SPONS_DFE_PN";
    public static String SPONSOR_STATE_FIELD = "SPONS_DFE_MAIL_US_STATE";

    private String field = PLAN_NAME_FIELD;
    private String value = "";
    private String scrollId = null;
    private boolean isScrollQuery = false;

    /**
     * The Query plan constructor that takes the lambda event object and initializes the constructor.
     * @param event The JSON event object.
     */
    public QueryPlanParams(JSONObject event) {
        if (event.get("queryStringParameters") != null) {
            JSONObject qps = (JSONObject)event.get("queryStringParameters");
            if ( qps.get("field") != null) {
                field = (String)qps.get("field");
            }
            if ( qps.get("value") != null) {
                value = (String)qps.get("value");
            }
            if ( qps.get("scrollId") != null) {
                scrollId = (String)qps.get("scrollId");
                if (scrollId != null) {
                    isScrollQuery = true;
                }
            }
        }
    }

    /**
     *
     * @return the field name. We support some simple field names that map to specific field for ease of use.
     */
    public String getFieldName() {
        if (field.equals("plan")) {
            return PLAN_NAME_FIELD;
        }
        if (field.equals("sponsor")) {
            return SPONSOR_NAME_FIELD;
        }
        if (field.equals("state")) {
            return SPONSOR_STATE_FIELD;
        }
        return field;
    }

    /**
     * @return the search string
     */
    public String getSearchString() {
        return value;
    }

    /**
     * @return true if this is a scroll query and false otherwise
     */
    public boolean isScrollQuery() {
        return isScrollQuery;
    }

    /**
     * @return the scroll id when this is a scroll query
     */
    public String getScrollId() {
        return scrollId;
    }
}
