package com.codeaddi.row_your_boat.controller.http;

import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.enums.Resource;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;

@Component
@Slf4j
public class AvailabilityClient extends HttpClient {

  public List<UpcomingAvailabilityDTO> getAllSessions() {
    try {
      String response =
          restTemplate.getForObject(
              getUrl("get_all_upcoming_sessions", Resource.SESSION_AVAILABILITY), String.class);
      List<UpcomingAvailabilityDTO> sessions =
          objectMapper.readValue(response, new TypeReference<List<UpcomingAvailabilityDTO>>() {});
      log.info("Successfully retrieved all upcoming sessions");
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
