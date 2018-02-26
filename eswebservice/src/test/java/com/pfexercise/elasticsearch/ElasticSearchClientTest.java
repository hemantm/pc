package com.pfexercise.elasticsearch;

import org.junit.*;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

/**
 * The elasticsearch client test
 */
public class ElasticSearchClientTest {
    @Rule
    public final EnvironmentVariables environmentVariables
            = new EnvironmentVariables();

    @Test
    public void getInstance() {
        environmentVariables.set("ES_HOST", "localhost");
        environmentVariables.set("ES_PORT", "9020");
        ElasticSearchClient client = ElasticSearchClient.getInstance();
        Assert.assertNotNull(client.getRestClient());
    }

    @Test
    public void getRestClient() {
        ElasticSearchClient client = ElasticSearchClient.getInstance("localhost", 9020);
        Assert.assertNotNull(client.getRestClient());
    }
}