package com.pfexercise.aws;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The Query plan params test
 */
public class QueryPlanParamsTest {
    public static final String HARVEY = "HARVEY";
    public static final String SCROLL_ID_VALUE = "xYzghDf";
    public static final String FIELD = "field";
    public static final String VALUE = "value";
    public static final String SCROLL_ID = "scrollId";
    public static final String QUERY_STRING_PARAMS = "queryStringParameters";

    private JSONObject event = null;

    @Before
    public void setUp() throws Exception {
        event = new JSONObject();
        JSONObject queryParams = new JSONObject();
        queryParams.put(FIELD, QueryPlanParams.PLAN_NAME_FIELD);
        queryParams.put(VALUE, HARVEY);
        event.put(QUERY_STRING_PARAMS, queryParams);
    }

    @Test
    public void getFieldName() {
        QueryPlanParams queryParams = new QueryPlanParams(event);
        Assert.assertNotNull(queryParams.getFieldName(), "Field parameter cannot be empty");
        Assert.assertEquals(queryParams.getFieldName(), QueryPlanParams.PLAN_NAME_FIELD);
    }

    @Test
    public void getSearchString() {
        QueryPlanParams queryParams = new QueryPlanParams(event);
        Assert.assertNotNull(queryParams.getSearchString(), "Search string cannot be empty");
        Assert.assertEquals(queryParams.getSearchString(), HARVEY);
    }

    @Test
    public void isScrollQuery() {
        QueryPlanParams params = new QueryPlanParams(event);
        Assert.assertFalse(params.isScrollQuery());
        event = new JSONObject();
        JSONObject queryParams = new JSONObject();
        queryParams.put(SCROLL_ID, SCROLL_ID_VALUE);
        event.put(QUERY_STRING_PARAMS, queryParams);
        params = new QueryPlanParams(event);
        Assert.assertTrue(params.isScrollQuery());
    }

    @Test
    public void getScrollId() {
        QueryPlanParams params = new QueryPlanParams(event);
        Assert.assertNull(params.getScrollId());

        event = new JSONObject();
        JSONObject queryParams = new JSONObject();
        queryParams.put(SCROLL_ID, SCROLL_ID_VALUE);
        event.put(QUERY_STRING_PARAMS, queryParams);
        params = new QueryPlanParams(event);
        Assert.assertEquals(SCROLL_ID_VALUE, params.getScrollId());
    }
}