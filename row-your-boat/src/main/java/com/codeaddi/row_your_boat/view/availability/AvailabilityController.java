package com.codeaddi.row_your_boat.view.availability;

import com.codeaddi.row_your_boat.controller.services.view.ViewService;
import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailabilityDTO;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AvailabilityController {
  @Autowired ViewService viewService;

  @GetMapping("/my-availability")
  public String myAvailability(Model model) {
    Map<Squad, List<UpcomingSessionAvailabilityDTO>> availabilitySessions =
        viewService.getAvailabilitySessions();

    Map<Squad, List<UpcomingSessionAvailabilityDTO>> sessionsWithAvailability =
        viewService.addAvailabilityForThisUser(
            1L,
            Squad.WOMENS,
            availabilitySessions); // this will be passed in from the frontend eventually

    model.addAttribute("availabilitySessions", sessionsWithAvailability);
    return "availability/my-availability";
  }
}
