package com.harshit.news;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import java.util.Collections;
import java.util.Map;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WiremockNews implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        configureFor(8089);

        stubFor(get(urlEqualTo("/news/2"))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type","application/json")
                                .withBody("{\"newsId\":\"2\"," +
                                        "\"title\":\"abcd\"," +
                                        "\"name\":\"quarkus\"," +
                                        "\"content\":null}")
                ));

        stubFor(post(urlEqualTo("/news/create"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(matchingJsonPath("$.title",equalTo("wiremock test")))
                .withRequestBody(matchingJsonPath("$.content",equalTo("qwertyuiop")))
                        .withRequestBody(matchingJsonPath("$.author",equalTo("quarkus")))
                                .withRequestBody(matchingJsonPath("$.category",equalTo("testing")))
                                        .willReturn(aResponse()
                                                .withHeader("Content-Type","application/json")
                                                .withBody("{\"title\":\"wiremock test\"}")));

        stubFor(get(urlMatching(".*"))
                .atPriority(10)
                .willReturn(aResponse().proxiedFrom("https://smart-city-app.onrender.com")));

        return Collections.singletonMap("https://smart-city-app.onrender.com", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if(wireMockServer != null){
            wireMockServer.stop();
        }
    }
}