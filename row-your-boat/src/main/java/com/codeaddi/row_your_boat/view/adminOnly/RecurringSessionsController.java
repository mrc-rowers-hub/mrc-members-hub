package com.codeaddi.row_your_boat.view.adminOnly;

import com.codeaddi.row_your_boat.controller.services.view.ViewService;
import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.http.inbound.RowingSession;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class RecurringSessionsController {

  @Autowired ViewService viewService;

  @GetMapping("/view-sessions-to-edit")
  public String viewSessions(Model model) {
    List<RowingSession> sessions = viewService.getAllSessions();

    model.addAttribute("sessions", sessions);

    return "adminOnly/view-sessions-to-edit";
  }

  @GetMapping("/standard-sessions")
  public String standardSessions(Model model) {
    Map<Squad, List<RowingSessions>> sessions = viewService.getAllStandardSessionsToDisplay();
    model.addAttribute("sessions", sessions);

    Long maxId = viewService.getMaxId();

    model.addAttribute("maxId", maxId);

    return "adminOnly/standard-sessions";
  }
}
