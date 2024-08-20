package com.codeaddi.row_your_boat.controller.http;

import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.enums.Resource;
import com.codeaddi.row_your_boat.model.rowers.Rower;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Component
@Slf4j
public class RowerClient extends  HttpClient {

    public List<Rower> getAllRowers(){
        try {
            String response =
                    restTemplate.getForObject(
                            getUrl("get_all", Resource.ROWERS), String.class);
            List<Rower> sessions =
                    objectMapper.readValue(response, new TypeReference<List<Rower>>() {});
            log.info("Successfully retrieved all rowers");
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
