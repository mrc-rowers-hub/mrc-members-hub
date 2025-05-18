package com.codeaddi.row_your_boat.view.adminOnly;

import com.codeaddi.row_your_boat.controller.services.view.ViewService;
import java.util.List;

import com.codeaddi.row_your_boat.controller.util.DateUtil;
import com.codeaddi.row_your_boat.model.http.inbound.Blade;
import com.codeaddi.row_your_boat.model.http.inbound.Boat;
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

    String sessionDate = DateUtil.extractDateOnly(date);
    String sessionStartTime = DateUtil.extractMilitaryTime(date);
    String sessionEndTime = DateUtil.extractMilitaryTimePlus2Hours(date);    //  todo - need to get the full session time,. like the end time? Might have to do another call to the scheduler service for this - for now, hardcoded at +2jhours from start

    List<Boat> boatsAvailableAtTime = viewService.getAllBoatsAvailableAtTime(sessionStartTime, sessionEndTime, sessionDate);
    List<Blade> bladesAvailableAtTime = viewService.getAllBladesAvailableAtTime(sessionStartTime, sessionEndTime, sessionDate);

    // Todo add a note saying 'think a resource should be a vailable? Check the status of all in PAGE TO BE DONE'
    model.addAttribute("sessionDate", sessionDate);
    model.addAttribute("sessionStartTime", sessionStartTime);
    model.addAttribute("sessionEndTime", sessionEndTime);
    model.addAttribute("boatsAvailable", boatsAvailableAtTime);
    model.addAttribute("bladesAvailable", bladesAvailableAtTime);

    return "adminOnly/session-availability";
  }
}
