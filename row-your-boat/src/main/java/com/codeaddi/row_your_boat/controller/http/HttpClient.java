package com.codeaddi.row_your_boat.controller.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class HttpClient {
    protected String sessionsPath = "standard_sessions/";

    @Value("${services.scheduler-sevice.baseUrl}")
    protected String schedulerServiceBaseUrl;

    protected RestTemplate restTemplate = new RestTemplate();
    protected ObjectMapper objectMapper = new ObjectMapper();
}
