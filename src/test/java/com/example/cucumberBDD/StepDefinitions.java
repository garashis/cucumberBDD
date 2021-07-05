package com.example.cucumberBDD;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

public class StepDefinitions {
    private final TestRestTemplate restTemplate;
    private final String basePath;

    public StepDefinitions(@LocalServerPort int port, TestRestTemplate restTemplate) {
        basePath = "http://localhost:" + port;
        this.restTemplate = restTemplate;
    }

    @Given("^I want to test (.+) event$")
    public void i_want_to_test_event(String event) {
        Data data = JsonDataReader.INSTANCE.getCustomerByName(event);
        Assert.assertEquals(data.getStatus(), restTemplate.getForEntity(basePath + data.getEndPoint(), String.class).getStatusCodeValue());
    }
}
