package com.codeaddi.row_your_boat.controller.sessions.http;

import com.codeaddi.row_your_boat.model.http.StandardResponse;
import com.codeaddi.row_your_boat.model.http.enums.Status;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
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

  public StandardResponse updateSession(RowingSession session) {
    String url = String.format(schedulerServiceBaseUrl + sessionsPath + "update_session");

    try {
      String requestJson = objectMapper.writeValueAsString(session);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

      ResponseEntity<String> responseEntity =
          restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

      String responseBody = responseEntity.getBody();
      StandardResponse standardResponse =
          objectMapper.readValue(responseBody, StandardResponse.class);

      if (responseEntity.getStatusCode().is2xxSuccessful()) {
        log.info("Successfully updated session. Response: {}", standardResponse);
      }

      return standardResponse;

    } catch (Exception e) {
      log.error("Unexpected error: " + e.getMessage(), e);
      return StandardResponse.builder().status(Status.ERROR).message("Unexpected error").build();
    }
  }
}
