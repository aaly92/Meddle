package io.intrepid.meddle.rest;

import io.intrepid.meddle.rules.MockServerRule;

public class TestRestClient {
    public static RestApi getRestApi(MockServerRule mockServer) {
        return RetrofitClient.getTestApi(mockServer.getServerUrl());
    }
}
