package com.codeaddi.row_your_boat.controller.http;

import com.codeaddi.row_your_boat.model.http.StandardResponse;
import com.codeaddi.row_your_boat.model.http.enums.Resource;
import com.codeaddi.row_your_boat.model.http.enums.Status;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class HttpClient {
//   Todo, when using other services, update to not just use scheduler service

  @Value("${services.scheduler-sevice.baseUrl}")
  protected String schedulerServiceBaseUrl;

  protected RestTemplate restTemplate = new RestTemplate();
  protected ObjectMapper objectMapper = new ObjectMapper();
  protected HttpHeaders headers = new HttpHeaders();

  protected String getUrl(String endpoint, Resource resource) {
    return String.format(schedulerServiceBaseUrl + resource.getEndpoint() + endpoint);
  }

  protected HttpEntity<String> getRequestEntity(String requestJson) {

    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

    return requestEntity;
  }

  protected String getForResource(String endpoint, Resource resource){
    return restTemplate.getForObject(
            getUrl(endpoint, resource), String.class);
  }

  protected StandardResponse getSingleStandardResponse(List<StandardResponse> standardResponseList) {
    List<StandardResponse> nonSuccessResponses = standardResponseList.stream()
            .filter(response -> response.getStatus() != Status.SUCCESS)
            .toList();

    if (!nonSuccessResponses.isEmpty()) {
      if (nonSuccessResponses.stream().anyMatch(response -> response.getStatus() == Status.ERROR)) {
        return StandardResponse.builder()
                .status(Status.ERROR)
                .message("something went wrong :(")
                .build();
      } else {
        String problemIds = nonSuccessResponses.stream()
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

  protected <T> List<T> getForResourceAndParse(String endpoint, TypeReference<List<T>> typeReference, Resource resource) {
    try {
      String response = getForResource(endpoint, resource);
      List<T> result = objectMapper.readValue(response, typeReference);
      log.info("Successfully retrieved data from endpoint: {}", endpoint);
      return result;
    } catch (RestClientResponseException e) {
      log.error("Scheduler service gave an unexpected response: {}", e.getStatusCode());
      return List.of();
    } catch (Exception e) {
      log.error("Unexpected error: {}", e.getMessage());
      return List.of();
    }
  }
}
