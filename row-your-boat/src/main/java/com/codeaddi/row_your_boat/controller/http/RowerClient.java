package com.codeaddi.row_your_boat.controller.http;

import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.enums.Resource;
import com.codeaddi.row_your_boat.model.rowers.Rower;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;

@Component
@Slf4j
public class RowerClient extends HttpClient {

  private final Resource resource = Resource.ROWERS;

  public List<Rower> getAllRowers() {
    return getForResourceAndParse("get_all", new TypeReference<List<Rower>>() {}, resource);
  }
}
