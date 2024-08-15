package com.codeaddi.row_your_boat.view.display;

import com.codeaddi.row_your_boat.controller.http.AvailabilityClient;
import com.codeaddi.row_your_boat.controller.sessions.AvailabilityService;
import com.codeaddi.row_your_boat.controller.sessions.SessionsService;
import com.codeaddi.row_your_boat.controller.http.SchedulerClient;
import com.codeaddi.row_your_boat.controller.sessions.UpcomingSessionsGrouper;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.availability.AvailabilityGroup;
import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;
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
  @Autowired private AvailabilityClient availabilityClient;

  public List<RowingSession> getAllSessions() {
    List<RowingSession> sessionsToReturn = schedulerClient.getAllSessions();

    sessionsToReturn.sort(Comparator.comparing(RowingSession::getStartTime));

    return sessionsToReturn;
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

  public Map<Squad, List<AvailabilityGroup>> getAvailabilitySessions(){
    List<UpcomingAvailabilityDTO> upcomingSessions = availabilityClient.getAllSessions();
    Map<UpcomingSessionsGrouper.UpcomingSessionKey, List<UpcomingAvailabilityDTO>> upcomingSessionKeyListMap =
            UpcomingSessionsGrouper.groupSessions(upcomingSessions);
    Map<Squad, List<AvailabilityGroup>> toReturn = AvailabilityService.mapAvailabilityGroupsToSquads(upcomingSessionKeyListMap);

    toReturn.forEach(
            (key, value) -> value.sort(Comparator.comparing(AvailabilityGroup::getDate)
    ));
    return toReturn;

  }
}
