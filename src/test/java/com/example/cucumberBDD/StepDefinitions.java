package com.example.cucumberBDD;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

public class StepDefinitions {
    private final String SERVER_URL = "http://localhost";
    private final RestTemplate restTemplate = new RestTemplate();
    @LocalServerPort
    private int port;

    @Autowired
    private RestClient httpClient;

    private int actualStatus;

    @Given("^I want to test (.+)$")
    public void i_have_cukes_in_my_belly(String event) throws Throwable {
        Data data = JsonDataReader.INSTANCE.getCustomerByName(event);
         int status = restTemplate.getForEntity(SERVER_URL + ":" + port + data.getEndPoint(), String.class).getStatusCodeValue();
        System.out.println(">>>>>>>>>> " + status);

        Assert.assertEquals(data.getStatus(), restTemplate.getForEntity(SERVER_URL + ":" + port + data.getEndPoint(), String.class).getStatusCodeValue());
    }
}
