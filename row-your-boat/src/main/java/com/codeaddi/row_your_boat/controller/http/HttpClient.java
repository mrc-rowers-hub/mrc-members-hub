package com.codeaddi.row_your_boat.controller.http;

import com.codeaddi.row_your_boat.model.http.enums.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class HttpClient {

    @Value("${services.scheduler-sevice.baseUrl}")
    protected String schedulerServiceBaseUrl;

    protected RestTemplate restTemplate = new RestTemplate();
    protected ObjectMapper objectMapper = new ObjectMapper();

    protected String getUrl(String endpoint, Resource resource){
        return String.format(schedulerServiceBaseUrl + resource.getEndpoint() + endpoint);
    }
}
