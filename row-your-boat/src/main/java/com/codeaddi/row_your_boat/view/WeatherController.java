package com.codeaddi.row_your_boat.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
public class WeatherController {


    @Value("${services.weather.baseUrl}")
    private String weatherServiceBaseUrl;

    @GetMapping("/weather")
    public RedirectView redirectToOtherService() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(weatherServiceBaseUrl);
        return redirectView;
    }
}
