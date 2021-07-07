package com.example.cucumberBDD;

import io.cucumber.messages.internal.com.google.gson.Gson;
import org.springframework.http.HttpMethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

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
}
