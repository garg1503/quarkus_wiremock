package com.harshit.github;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Collections;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WiremockGithub implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        configureFor(8089);

        stubFor(
                get(urlEqualTo("/gitUser/garg"))
                        .willReturn(
                                aResponse()
                                        .withHeader("Content-Type","application/json")
                                        .withBody("{\"login\":\"garg\"," +
                                                "\"id\":136229559," +
                                                "\"name\":\"Harsh\"," +
                                                "\"company\":null," +
                                                "\"blog\":\"\"," +
                                                "\"location\":null" +
                                                ",\"email\":null," +
                                                "\"bio\":null}")
                        )
        );

        stubFor(
                get(urlMatching(".*"))
                        .atPriority(20)
                        .willReturn(aResponse().proxiedFrom("https://api.github.com"))
        );

        return Collections.singletonMap("https://api.github.com", wireMockServer.baseUrl());
    }

    @Override
    public void stop() {
        if(wireMockServer != null){
            wireMockServer.stop();
        }
    }
}
