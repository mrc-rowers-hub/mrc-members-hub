package com.codeaddi.row_your_boat.controller.http;

import com.codeaddi.row_your_boat.model.http.StandardResponse;
import com.codeaddi.row_your_boat.model.http.enums.Resource;
import com.codeaddi.row_your_boat.model.http.enums.Status;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;

@Component
@Slf4j
public class SchedulerClient extends HttpClient {

  public List<RowingSession> getAllSessions() {
    try {
      String response =
          restTemplate.getForObject(
              getUrl("get_all_sessions", Resource.STANDARD_SESSIONS), String.class);
      List<RowingSession> sessions =
          objectMapper.readValue(response, new TypeReference<List<RowingSession>>() {});
      log.info("Successfully retrieved all sessions from scheduler service");
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
    String url = getUrl("update_session", Resource.STANDARD_SESSIONS);
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

  public StandardResponse deleteSession(Long sessionId) {
    String url =
        getUrl(String.format("delete_session?sessionId=%d", sessionId), Resource.STANDARD_SESSIONS);

    try {
      ResponseEntity<String> responseEntity =
          restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);

      String responseBody = responseEntity.getBody();
      StandardResponse standardResponse =
          objectMapper.readValue(responseBody, StandardResponse.class);

      if (responseEntity.getStatusCode().is2xxSuccessful()) {
        log.info("Successfully deleted session. Response: {}", standardResponse.getMessage());
      }

      return standardResponse;

    } catch (Exception e) {

      if (e.getMessage().contains("Session not found")) {
        log.error("Session not found");

        return StandardResponse.builder()
            .status(Status.ERROR)
            .message("Session not found, nothing to delete")
            .build();
      } else {
        log.error("Unexpected error: " + e.getMessage(), e);
        return StandardResponse.builder().status(Status.ERROR).message("Unexpected error").build();
      }
    }
  }
}
