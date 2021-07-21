package com.example.cucumberBDD;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public final class JsonDataReader {

/*

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
*/

    public static String loadJSONTemplate(String template) {
        String filePath = "src/test/resources/" + template + "/" + "template.json";
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Json doesn't match to schema : " + e.getMessage());
        }
    }

    protected static void validateJson(String schemaName, String json) {
        String schemaPath = "src/test/resources/" + schemaName + "/" + "schema.json";
        JsonSchema schema = getJsonSchema(schemaPath);
        Set<ValidationMessage> errors = schema.validate(getJsonNode(json));
        if (!errors.isEmpty()) {
            throw new RuntimeException("Json doesn't match to schema : " + errors);
        }
    }

    protected static JsonSchema getJsonSchema(String schemaPath) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        try (InputStream bufferReader = new FileInputStream(schemaPath)) {
            return factory.getSchema(bufferReader);
        } catch (Exception e) {
            throw new RuntimeException("Error reading JSON schema: " + e.getMessage());
        }
    }

    protected static JsonNode getJsonNode(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON string to JsonNode : " + e.getMessage());
        }
    }
}
