package com.example.cucumberBDD;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class RestClient {
    private final String SERVER_URL = "http://localhost";
    private final String GET_TIME_ENDPOINT = "/getTime";
    private final RestTemplate restTemplate = new RestTemplate();

    @LocalServerPort
    private int port;

    private String thingsEndpoint() {
        return SERVER_URL + ":" + port + GET_TIME_ENDPOINT;
    }

    public int getTime(String endPoint) {
        return restTemplate.getForEntity(SERVER_URL + ":" + port +  endPoint, String.class).getStatusCodeValue();
    }
}
