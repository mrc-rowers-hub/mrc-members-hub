package com.codeaddi.row_your_boat.controller.http.schedulerService;

import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class SchedulerClient {

  private String endpoint = "standard_sessions/"; // update the name of this

  @Value("${services.scheduler-sevice.baseUrl}")
  private String schedulerServiceBaseUrl;

  RestTemplate restTemplate = new RestTemplate();

  public List<RowingSessions> getAllSessions() {
    String url = String.format(schedulerServiceBaseUrl + endpoint + "get_all_sessions");
    try {
      RowingSessions[] sessionsArray = restTemplate.getForObject(url, RowingSessions[].class);
      log.info("Successfully retrieved and mapped response from scheduler service");
      return Arrays.asList(sessionsArray);
    } catch (RestClientResponseException e) {
      log.error("Scheduler service gave an unexpected response: {}", e.getStatusCode());
      return List.of();
    } catch (NullPointerException e) {
      log.error("Scheduler service returned no data");
      return List.of();
    } catch (Exception e) {
      log.error("Unexpected error: " + e.getMessage());
      throw e;
    }
  }
}
