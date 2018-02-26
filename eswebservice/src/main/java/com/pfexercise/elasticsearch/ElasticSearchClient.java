package com.pfexercise.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * ElasticSearch client. We just need one instance to connect to the service.
 *
 */
public class ElasticSearchClient {
    // static variable client
    private static ElasticSearchClient client = null;
    private RestHighLevelClient restClient = null;
    private static String REST_CLIENT_PROTOCOL = "http";

    /**
     * No one can instantiate this
     */
    private ElasticSearchClient(String host, int port) {
        restClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, port, REST_CLIENT_PROTOCOL)));
    }

    /**
     * @TODO ES_HOST should be array of hosts
     * @return the instance of the Elasticsearch client
     */
    public static ElasticSearchClient getInstance() {
        String eshost = System.getenv("ES_HOST");
        String esport = System.getenv("ES_PORT");
        int port = Integer.parseInt(esport);
        return getInstance(eshost, port);
    }

    public static ElasticSearchClient getInstance(String host, int port) {
        if (client == null)
            client = new ElasticSearchClient(host, port);
        return client;
    }

    /**
     * @return the high level rest client for elastic search
     */
    public RestHighLevelClient getRestClient() {
        return restClient;
    }
}
