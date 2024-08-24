package com.codeaddi.row_your_boat.controller.http;

import com.codeaddi.row_your_boat.model.http.AvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.StandardResponse;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailability;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.enums.Resource;
import com.codeaddi.row_your_boat.model.http.enums.Status;
import com.codeaddi.row_your_boat.model.http.inbound.PastSession;
import com.codeaddi.row_your_boat.model.http.inbound.PastSessionAvailability;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;

@Component
@Slf4j
public class AvailabilityClient extends HttpClient {

  private final Resource resource = Resource.SESSION_AVAILABILITY;

  public List<UpcomingSessionAvailabilityDTO> getAllUpcomingSessions() {
    return getForResourceAndParse(
        "get_all_upcoming_sessions",
        new TypeReference<List<UpcomingSessionAvailabilityDTO>>() {},
        resource);
  }

  public List<PastSession> getAllUpcomingPastSessions() {
    return getForResourceAndParse(
        "get_upcoming_past_sessions", new TypeReference<List<PastSession>>() {}, resource);
  }

  public List<PastSessionAvailability> getAllUpcomingPastSessionAvailability() {
    return getForResourceAndParse(
        "get_rowers_availability", new TypeReference<List<PastSessionAvailability>>() {}, resource);
  }

  public List<UpcomingSessionAvailability> getUpcomingAvailabilityForRower(Long rowerId) {
    try {
      String url = getUrl("get_upcoming_availability", resource) + "?rowerId=" + rowerId;
      String response = restTemplate.getForObject(url, String.class);
      List<UpcomingSessionAvailability> availableSessions =
          objectMapper.readValue(
              response, new TypeReference<List<UpcomingSessionAvailability>>() {});
      log.info("Successfully retrieved all upcoming sessions");
      return availableSessions;
    } catch (RestClientResponseException e) {
      log.error("Scheduler service gave an unexpected response: {}", e.getStatusCode());
      return List.of();
    } catch (Exception e) {
      log.error("Unexpected error: " + e.getMessage());
      return List.of();
    }
  }

  public StandardResponse saveAvailability(List<AvailabilityDTO> availabilityData) {
    String url = getUrl("save_availability", resource);
    try {
      String requestJson = objectMapper.writeValueAsString(availabilityData);
      HttpEntity<String> requestEntity = getRequestEntity(requestJson);
      ResponseEntity<String> responseEntity =
          restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

      String responseBody = responseEntity.getBody();
      List<StandardResponse> standardResponseList =
          objectMapper.readValue(responseBody, new TypeReference<List<StandardResponse>>() {});

      if (responseEntity.getStatusCode().is2xxSuccessful()) {
        log.info("Successfully updated session. Response: {}", standardResponseList);
      }

      return getSingleStandardResponse(standardResponseList);

    } catch (Exception e) {
      log.error("Unexpected error: " + e.getMessage(), e);
      return StandardResponse.builder().status(Status.ERROR).message("Unexpected error").build();
    }
  }
}
