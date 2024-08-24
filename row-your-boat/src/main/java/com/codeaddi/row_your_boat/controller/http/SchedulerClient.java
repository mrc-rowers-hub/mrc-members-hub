package com.codeaddi.row_your_boat.controller.http;

import com.codeaddi.row_your_boat.model.http.StandardResponse;
import com.codeaddi.row_your_boat.model.http.enums.Resource;
import com.codeaddi.row_your_boat.model.http.enums.Status;
import com.codeaddi.row_your_boat.model.http.inbound.RowingSession;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerClient extends HttpClient {

  private final Resource resource = Resource.STANDARD_SESSIONS;

  public List<RowingSession> getAllSessions() {
    return getForResourceAndParse(
        "get_all_sessions", new TypeReference<List<RowingSession>>() {}, resource);
  }

  public StandardResponse updateSession(RowingSession session) {
    String url = getUrl("update_session", Resource.STANDARD_SESSIONS);
    try {
      String requestJson = objectMapper.writeValueAsString(session);

      HttpEntity<String> requestEntity = getRequestEntity(requestJson);

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
