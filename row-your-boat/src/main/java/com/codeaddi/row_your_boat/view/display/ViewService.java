package com.codeaddi.row_your_boat.view.display;

import com.codeaddi.row_your_boat.controller.sessions.SessionsService;
import com.codeaddi.row_your_boat.controller.sessions.http.SchedulerClient;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewService {

  @Autowired private SchedulerClient schedulerClient;

  public List<RowingSession> getAllSessions(){
    return schedulerClient.getAllSessions();
  }

  public Map<Squad, List<RowingSessions>> getAllStandardSessionsToDisplay() {
    List<RowingSession> rowingSessions = schedulerClient.getAllSessions();
    List<RowingSessions> groupedSessions =
        SessionsService.mapRowingSessionToSessions(rowingSessions);
    Map<Squad, List<RowingSessions>> standardSessions =
        SessionsService.getRowingSessionsPerSquad(groupedSessions);

    // sort by time
    standardSessions.forEach(
        (key, value) -> value.sort(Comparator.comparing(RowingSessions::getStartTime)));

    // sort into day order
    standardSessions.forEach(
        (key, value) ->
            value.sort(Comparator.comparing(rowingSession -> rowingSession.getDay().ordinal())));

    return standardSessions;
  }

  public Long getMaxId() {
    List<RowingSession> rowingSessions = schedulerClient.getAllSessions();

    return rowingSessions.stream()
        .max(Comparator.comparingLong(RowingSession::getId))
        .map(RowingSession::getId)
        .orElse(0L);
  }
}
