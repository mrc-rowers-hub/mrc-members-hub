package com.codeaddi.row_your_boat.controller.http.schedulerService;

import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class SchedulerClient {

  private String sessionsPath = "standard_sessions/";

  @Value("${services.scheduler-sevice.baseUrl}")
  private String schedulerServiceBaseUrl;

  RestTemplate restTemplate = new RestTemplate();
  ObjectMapper objectMapper = new ObjectMapper(); // Create an ObjectMapper instance

  public List<RowingSessions> getAllSessions() {
    String url = String.format(schedulerServiceBaseUrl + sessionsPath + "get_all_sessions");
    try {
      String response = restTemplate.getForObject(url, String.class); // Get JSON as String
      List<RowingSessions> sessions = objectMapper.readValue(response, new TypeReference<List<RowingSessions>>() {});
      log.info("Successfully retrieved and mapped response from scheduler service");
      return sessions;
    } catch (RestClientResponseException e) {
      log.error("Scheduler service gave an unexpected response: {}", e.getStatusCode());
      return List.of();
    } catch (Exception e) {
      log.error("Unexpected error: " + e.getMessage());
      return List.of();
    }
  }
}
