package com.pcexercise.elasticsearch;

import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

/**
 * This interface defines the API for querying the QueryPlans
 */
public interface QueryPlans {
    /**
     * Allows searching the plans by a search string on a specified search field.
     *
     * @param searchField The plan search field
     * @param searchString The plan search string
     * @return The response containing partial records. Use the scroll API to retrieve the next batch.
     * @throws IOException if there was a problem communicating with the service.
     */
    public  SearchResponse searchPlans(String searchField, String searchString) throws IOException;

    /**
     * Return the next batch of the plans using the scroll Id returned to the client.
     *
     * @param scrollId The scroll id from the previous batch
     * @return The search response from the elasticsearch
     * @throws IOException thrown if there is a problem communicating with the cluster.
     */
    public SearchResponse scrollPlans(String scrollId) throws IOException;
}
