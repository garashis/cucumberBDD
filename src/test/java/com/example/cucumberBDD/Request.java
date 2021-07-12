package com.example.cucumberBDD;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Request {
    private final String url;
    private final HttpMethod method;
    private final HttpHeaders httpHeaders;
    private final JsonNode body;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Request(@JsonProperty("url") String url,
                   @JsonProperty("method") HttpMethod method,
                   @JsonProperty("headers") Map<String, String> headers,
                   @JsonProperty("body") JsonNode body) {
        this.url = url;
        this.method = method;
        httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public JsonNode getBody() {
        return body;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }
}
