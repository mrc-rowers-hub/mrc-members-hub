package com.codeaddi.row_your_boat.view.adminOnly;

import com.codeaddi.row_your_boat.controller.services.view.ViewService;
import java.util.List;

import com.codeaddi.row_your_boat.model.http.enums.resources.EquipmentType;
import com.codeaddi.row_your_boat.model.http.inbound.ResourceInUse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class WeeklyPlanController {

  @Autowired ViewService viewService;

  @GetMapping("/make-weekly-plan")
  public String makeNewSessions(Model model) {
    List<String> sessionDates = viewService.getAllPastSessionsDates();

    model.addAttribute("list", sessionDates);
    return "adminOnly/make-new-sessions";
  }

  @PostMapping("/session-availability")
  public String showSessionAvailability(@RequestParam("date") String date, Model model) {
    List<String> availableRowers = viewService.getAllAvailableRowersForDate(date);
    model.addAttribute("availabilities", availableRowers);

    List<ResourceInUse> boatsAvailableAtTime = viewService.getResourcesAvailable(EquipmentType.BOAT);
    List<ResourceInUse> bladesAvailableAtTime = viewService.getResourcesAvailable(EquipmentType.BLADE);
    log.info(viewService.getAllBoatsAvailableAtTime("", "", "").toString());

    // a note saying 'think a resource should be a vailable? Check the status of all in PAGE TO BE DONE'


    return "adminOnly/session-availability";
  }
}
