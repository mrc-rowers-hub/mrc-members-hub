package com.codeaddi.row_your_boat.view;

import com.codeaddi.row_your_boat.controller.http.AvailabilityClient;
import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailabilityDTO;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.http.inbound.RowingSession;
import com.codeaddi.row_your_boat.view.display.ViewService;
import java.util.List;
import java.util.Map;
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
  //  Todo start splitting these into separate controllers by topic

  @Autowired ViewService viewService;

  @Value("${services.weather.baseUrl}")
  private String weatherServiceBaseUrl;

  @Autowired private AvailabilityClient availabilityClient;

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
  public String myAvailability(Model model) {
    Map<Squad, List<UpcomingSessionAvailabilityDTO>> availabilitySessions =
        viewService.getAvailabilitySessions();

    Map<Squad, List<UpcomingSessionAvailabilityDTO>> sessionsWithAvailability =
        viewService.addAvailabilityForThisUser(1L, Squad.WOMENS, availabilitySessions);

    model.addAttribute("availabilitySessions", sessionsWithAvailability);
    return "availability/my-availability";
  }

  @PostMapping("/session-availability")
  public String showSessionAvailability(@RequestParam("date") String date, Model model) {
    List<String> availableRowers = viewService.getAllAvailableRowersForDate(date);
    model.addAttribute("availabilities", availableRowers);
    return "adminOnly/session-availability";
  }

  @GetMapping("/standard-sessions")
  public String standardSessions(Model model) {
    Map<Squad, List<RowingSessions>> sessions = viewService.getAllStandardSessionsToDisplay();
    model.addAttribute("sessions", sessions);

    Long maxId = viewService.getMaxId();

    model.addAttribute("maxId", maxId);

    return "adminOnly/standard-sessions";
  }

  @GetMapping("/view-sessions-to-edit")
  public String viewSessions(Model model) {
    List<RowingSession> sessions = viewService.getAllSessions();

    model.addAttribute("sessions", sessions);

    return "adminOnly/view-sessions-to-edit";
  }

  @GetMapping("/make-weekly-plan")
  public String makeNewSessions(Model model) {
    List<String> sessionDates = viewService.getAllPastSessionsDates();

    model.addAttribute("list", sessionDates);
    return "adminOnly/make-new-sessions";
  }

  @GetMapping("/boats")
  public String boats() {
    return "resources/boats";
  }

  @GetMapping("/blades")
  public String blades() {
    return "resources/blades";
  }
}
