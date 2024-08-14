package com.codeaddi.row_your_boat.controller.sessions.http;

import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
  ObjectMapper objectMapper = new ObjectMapper();

  public List<RowingSession> getAllSessions() {
    String url = String.format(schedulerServiceBaseUrl + sessionsPath + "get_all_sessions");
    try {
      String response = restTemplate.getForObject(url, String.class);
      List<RowingSession> sessions =
          objectMapper.readValue(response, new TypeReference<List<RowingSession>>() {});
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

  public void updateSession(RowingSession session) {
    String url = String.format(schedulerServiceBaseUrl + sessionsPath + "update_session");

    try {
      String requestJson = objectMapper.writeValueAsString(session);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

      HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

      restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);

      log.info("Successfully updated session");
    } catch (Exception e) {
      log.error("Unexpected error: " + e.getMessage());
    }
  }

}
