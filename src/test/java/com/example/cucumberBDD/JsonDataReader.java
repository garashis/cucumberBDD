package com.example.cucumberBDD;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.cucumber.messages.internal.com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public enum JsonDataReader {
    INSTANCE;
    private List<Data> customerList;

    private List<Data> getCustomerData() {
        if (customerList != null) {
            return customerList;
        }
        Gson gson = new Gson();
        try (BufferedReader bufferReader = new BufferedReader(new FileReader("src/test/resources/data.json"))) {
            Data[] customers = gson.fromJson(bufferReader, Data[].class);
            return Arrays.asList(customers);
        } catch (Exception e) {
            throw new RuntimeException("Json file not found at path : " + "data.json");
        }
    }

    public final Data getCustomerByName(String name) {
        return getCustomerData().stream().filter(x -> x.getName().equalsIgnoreCase(name)).findAny().get();
    }

    public final Data loadEventData(String event) {
        String fileName = "src/test/resources/" + event + ".json";
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(fileName))) {
            Gson gson = new Gson();
            return gson.fromJson(bufferReader, Data.class);
        } catch (Exception e) {
            throw new RuntimeException("Json file not found at path : " + fileName);
        }
    }

    protected void validateJson(String name) {
        String schemaPath = "src/test/resources/" + name + "/" + name + ".schema.json";
        String jsonPath = "src/test/resources/" + name + "/" + name + ".json";
        JsonSchema schema = getJsonSchemaFromClasspath(schemaPath);
        JsonNode node = getJsonNodeFromClasspath(jsonPath);
        Set<ValidationMessage> errors = schema.validate(node);
        if (!errors.isEmpty()) {
            throw new RuntimeException("Json doesn't match to schema : " + errors);
        }
    }

    protected JsonSchema getJsonSchemaFromClasspath(String schemaPath) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        try (InputStream bufferReader = new FileInputStream(schemaPath)) {
            return factory.getSchema(bufferReader);
        } catch (Exception e) {
            throw new RuntimeException("Json file not found at path : " + schemaPath);
        }

    }

    protected JsonNode getJsonNodeFromClasspath(String jsonPath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream bufferReader = new FileInputStream(jsonPath)) {
            return mapper.readTree(bufferReader);
        } catch (Exception e) {
            throw new RuntimeException("Json file not found at path : " + jsonPath);
        }
    }
}
