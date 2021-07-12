package com.example.cucumberBDD;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class StepDefinitions {
    public static final String JSON_TEMPLATE = "jsonTemplate";
    private final TestRestTemplate restTemplate;
    private final String basePath;
    private String templateName;
    private ObjectMapper mapper = new ObjectMapper();

    public StepDefinitions(@LocalServerPort int port, TestRestTemplate restTemplate) {
        basePath = "http://localhost:" + port;
        this.restTemplate = restTemplate;
    }

    @Given("product details in JSON template and validate")
    public void tableWithExample(Map<String, String> row) {
        templateName = row.get(JSON_TEMPLATE);
        processRow(JsonDataReader.loadJSONTemplate(templateName), row);
    }

//    @Given("^I have the following details about products$")
//    public void haveBooksInTheStoreByMap(DataTable table) {
//        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
//        String template = JsonDataReader.loadJSONTemplate("product");
//        for (Map<String, String> row : rows) {
//            processRow(template, row);
//        }
//       System.out.println(template);
//    }

    private void processRow(String jsonTemplate, Map<String, String> row) {
        for (Map.Entry<String, String> entry : row.entrySet()) {
            jsonTemplate = jsonTemplate.replace(String.format("{%s}", entry.getKey()), entry.getValue());
        }
//        System.out.println(jsonTemplate);
        JsonDataReader.validateJson(templateName, jsonTemplate);

        Template template = null;
        try {
            template = mapper.readValue(jsonTemplate, Template.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        System.out.println(template);
        HttpEntity<JsonNode> request = new HttpEntity<>(template.getRequest().getBody(), template.getRequest().getHttpHeaders());
        ResponseEntity<String> resp = restTemplate.exchange(template.getRequest().getUrl(), template.getRequest().getMethod(), request, String.class);
//        System.out.println(resp);
    }
}
