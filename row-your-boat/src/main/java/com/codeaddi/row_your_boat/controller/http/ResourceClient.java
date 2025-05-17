package com.codeaddi.row_your_boat.controller.http;

import com.codeaddi.row_your_boat.model.http.enums.Resource;
import com.codeaddi.row_your_boat.model.http.inbound.Boat;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ResourceClient extends HttpClient {
    // for boats & blades
    private final Resource boatResource = Resource.BOATS;
    private final Resource bladeResource = Resource.BLADES;

    public List<Boat> getAllBoats() {
        return getForResourceAndParse(
                "get_all",
                new TypeReference<List<Boat>>() {},
                boatResource);
    }
}
