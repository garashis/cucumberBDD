package com.example.cucumberBDD;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Template {
    private final Request request;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Template(@JsonProperty("request") final Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }
}
