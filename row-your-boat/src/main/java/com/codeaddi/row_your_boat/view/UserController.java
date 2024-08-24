package com.codeaddi.row_your_boat.view;

import com.codeaddi.row_your_boat.controller.http.AvailabilityClient;
import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.inbound.RowingSession;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
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

  @GetMapping("/")
  public String index(Model model) {
    return "login";
  }

  @PostMapping("/home")
  public String home(@RequestParam("username") String username, Model model) {
    model.addAttribute("username", username);
    return "home";
  }

}
