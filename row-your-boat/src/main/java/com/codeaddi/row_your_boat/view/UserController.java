package com.codeaddi.row_your_boat.view;

import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;

import java.util.List;
import java.util.Map;

import com.codeaddi.row_your_boat.view.display.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
public class UserController {

  @Autowired
  ViewService viewService;

  @Value("${services.weather.baseUrl}")
  private String weatherServiceBaseUrl;

  @GetMapping("/")
  public String index(Model model) {
    return "login";
  }

  @PostMapping("/home")
  public String home(@RequestParam("username") String username, Model model) {
    model.addAttribute("username", username);
    return "home";
  }

  @GetMapping("/weather")
  public RedirectView redirectToOtherService() {
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl(weatherServiceBaseUrl);
    return redirectView;
  }

  @GetMapping("/my-availability")
  public String myAvailability() {
    return "my-availability";
  }

  @GetMapping("/standard-sessions")
  public String standardSessions(Model model) {
    Map<Squad, List<RowingSessions>> sessions = viewService.getAllStandardSessionsToDisplay();
    model.addAttribute("sessions", sessions);

    return "standard-sessions";
  }
}
