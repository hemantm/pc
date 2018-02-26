package com.pfexercise.elasticsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class QueryPlansImplTest {

    @Test
    public void searchPlans() throws IOException {
        RestHighLevelClient mockClient = mock(RestHighLevelClient.class);
        QueryPlans queryPlans = new QueryPlansImpl(mockClient);
        when(mockClient.search(any())).thenReturn(new SearchResponse());
        SearchResponse response = queryPlans.searchPlans("PLAN_NAME", "HARVEY");
        Assert.assertNotNull(response);
    }

    @Test
    public void scrollPlans() throws IOException {
        RestHighLevelClient mockClient = mock(RestHighLevelClient.class);
        QueryPlans queryPlans = new QueryPlansImpl(mockClient);
        when(mockClient.searchScroll(any())).thenReturn(new SearchResponse());
        SearchResponse response = queryPlans.scrollPlans("xyDffgqw");
        Assert.assertNotNull(response);
    }
}