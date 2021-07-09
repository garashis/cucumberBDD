package com.example.cucumberBDD;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;
import java.util.Map;

public class StepDefinitions {
    private final TestRestTemplate restTemplate;
    private final String basePath;

    public StepDefinitions(@LocalServerPort int port, TestRestTemplate restTemplate) {
        basePath = "http://localhost:" + port;
        this.restTemplate = restTemplate;
    }

    @Given("^I have the following details about products$")
    public void haveBooksInTheStoreByMap(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        String template = JsonDataReader.INSTANCE.loadJSONTemplate("product");
        for (Map<String, String> row : rows) {
            String jsonTemplate = new String(template);
            for (Map.Entry<String, String> entry : row.entrySet()) {
                jsonTemplate = jsonTemplate.replace(String.format("{%s}", entry.getKey()), entry.getValue());
            }
            System.out.println(jsonTemplate);
            JsonDataReader.INSTANCE.validateJson("product", jsonTemplate);
        }
//        System.out.println(template);
    }
}
