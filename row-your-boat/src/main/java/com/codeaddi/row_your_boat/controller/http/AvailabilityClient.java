package com.codeaddi.row_your_boat.controller.http;

import com.codeaddi.row_your_boat.model.http.AvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.StandardResponse;
import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.inbound.PastSession;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailability;
import com.codeaddi.row_your_boat.model.http.enums.Resource;
import com.codeaddi.row_your_boat.model.http.enums.Status;
import com.codeaddi.row_your_boat.model.http.inbound.PastSessionAvailability;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;

@Component
@Slf4j
public class AvailabilityClient extends HttpClient {

  public List<UpcomingAvailabilityDTO> getAllUpcomingSessions() {
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

  public List<PastSession> getAllUpcomingPastSessions() {
    try {
      String response =
              restTemplate.getForObject(
                      getUrl("get_upcoming_past_sessions", Resource.SESSION_AVAILABILITY), String.class);
      List<PastSession> sessions =
              objectMapper.readValue(response, new TypeReference<List<PastSession>>() {});
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

  public List<PastSessionAvailability> getAllUpcomingPastSessionAvailability() {
    try {
      String response =
              restTemplate.getForObject(
                      getUrl("get_rowers_availability", Resource.SESSION_AVAILABILITY), String.class);
      List<PastSessionAvailability> sessions =
              objectMapper.readValue(response, new TypeReference<List<PastSessionAvailability>>() {});
      log.info("Successfully retrieved all upcoming session availability");
      return sessions;
    } catch (RestClientResponseException e) {
      log.error("Scheduler service gave an unexpected response: {}", e.getStatusCode());
      return List.of();
    } catch (Exception e) {
      log.error("Unexpected error: " + e.getMessage());
      return List.of();
    }
  }

  // add one in to get all past sessions

  public List<UpcomingSessionAvailability> getUpcomingAvailabilityForRower(Long rowerId) {
    try {
      String url =
          getUrl("get_upcoming_availability", Resource.SESSION_AVAILABILITY)
              + "?rowerId="
              + rowerId;

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

  public List<UpcomingSessionAvailability> getAvailabilityForAllRowersForSessionPlanning() {
    try {
      String response = restTemplate.getForObject(getUrl("get_upcoming_availability", Resource.SESSION_AVAILABILITY), String.class);
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

    String url = getUrl("save_availability", Resource.SESSION_AVAILABILITY);
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

  private StandardResponse getSingleStandardResponse(List<StandardResponse> standardResponseList) {
    List<StandardResponse> nonSuccessResponses =
        standardResponseList.stream()
            .filter(response -> response.getStatus() != Status.SUCCESS)
            .toList();

    if (!nonSuccessResponses.isEmpty()) {
      if (nonSuccessResponses.stream().anyMatch(response -> response.getStatus() == Status.ERROR)) {
        return StandardResponse.builder()
            .status(Status.ERROR)
            .message("something went wrong :(")
            .build();
      } else {
        String problemIds =
            nonSuccessResponses.stream()
                .map(StandardResponse::getId)
                .collect(Collectors.joining(","));
        return StandardResponse.builder()
            .message("Issues updating the following IDs: " + problemIds)
            .id(problemIds)
            .build();
      }
    } else {
      return StandardResponse.builder()
          .message("All updated successfully")
          .status(Status.SUCCESS)
          .build();
    }
  }
}
