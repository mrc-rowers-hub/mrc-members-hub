package com.codeaddi.row_your_boat.controller.http;

import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class HttpClient {
    protected String sessionsPath = "standard_sessions/";

    @Value("${services.scheduler-sevice.baseUrl}")
    protected String schedulerServiceBaseUrl;

    protected RestTemplate restTemplate = new RestTemplate();
    protected ObjectMapper objectMapper = new ObjectMapper();

    protected String getUrl(String endpoint){
        return String.format(schedulerServiceBaseUrl + sessionsPath + endpoint);
    }
}
