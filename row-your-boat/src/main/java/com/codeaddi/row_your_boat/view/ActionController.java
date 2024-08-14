package com.codeaddi.row_your_boat.view;

import com.codeaddi.row_your_boat.controller.sessions.http.SchedulerClient;
import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.http.StandardResponse;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/internal")
@Slf4j
public class ActionController {

  @Autowired SchedulerClient schedulerClient;

  @PostMapping("/add-rowing-session")
  public String addRowingSession(
      @RequestParam String day,
      @RequestParam String startTime,
      @RequestParam String endTime,
      @RequestParam String squad,
      @RequestParam String level,
      @RequestParam String sessionType,
      @RequestParam Long maxId,
      RedirectAttributes redirectAttributes) {
    log.info("Request to add new session received");

    RowingSession newSession =
        RowingSession.builder()
            .id(maxId + 1)
            .day(day)
            .startTime(startTime)
            .endTime(endTime)
            .squad(Squad.valueOf(squad))
            .level(RowerLevel.valueOf(level))
            .sessionType(SessionType.valueOf(sessionType))
            .build();
    StandardResponse response = schedulerClient.updateSession(newSession);

    if (response.getStatus().toString().contains("SUCCESS")) {
      redirectAttributes.addFlashAttribute("successMessage", response.getMessage());

    } else {
      redirectAttributes.addFlashAttribute("errorMessage", "New session added successfully!");
    }

    return "redirect:/standard-sessions";
  }

  @PostMapping("/update-rowing-session")
  @ResponseBody
  public StandardResponse updateRowingSession(
          @RequestParam String id,
          @RequestParam String day,
          @RequestParam String startTime,
          @RequestParam String endTime,
          @RequestParam String squad,
          @RequestParam String level,
          @RequestParam String sessionType) {
    log.info("Request to update session received");
    log.info("ID: {}", id);

    // Create and populate the RowingSession object
    RowingSession updatedSession = RowingSession.builder()
            .id(Long.valueOf(id))
            .day(day)
            .startTime(startTime)
            .endTime(endTime)
            .squad(Squad.valueOf(squad))
            .level(RowerLevel.valueOf(level))
            .sessionType(SessionType.valueOf(sessionType))
            .build();

    // Assume updateSession() is a method to save the updated session
    StandardResponse response = schedulerClient.updateSession(updatedSession);

    // Return the response as JSON
    return response;
  }
}
