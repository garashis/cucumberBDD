package com.example.cucumberBDD;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class StepDefinitions {
    private final TestRestTemplate restTemplate;
    private final String basePath;

    public StepDefinitions(@LocalServerPort int port, TestRestTemplate restTemplate) {
        basePath = "http://localhost:" + port;
        this.restTemplate = restTemplate;
    }

    @Given("^I want to test (.+) event$")
    public void i_want_to_test_event(String event) {
        Data data = JsonDataReader.INSTANCE.loadEventData(event);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EventRequest> request = new HttpEntity<>(data.getEventRequest(), headers);
        Assert.assertEquals(data.getStatus(), restTemplate.postForEntity(basePath + data.getEndPoint(), request, String.class).getStatusCodeValue());
    }
}
