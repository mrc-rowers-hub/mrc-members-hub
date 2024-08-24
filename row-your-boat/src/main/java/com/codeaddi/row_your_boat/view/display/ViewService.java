package com.codeaddi.row_your_boat.view.display;

import com.codeaddi.row_your_boat.controller.http.AvailabilityClient;
import com.codeaddi.row_your_boat.controller.http.RowerClient;
import com.codeaddi.row_your_boat.controller.http.SchedulerClient;
import com.codeaddi.row_your_boat.controller.services.AvailabilityService;
import com.codeaddi.row_your_boat.controller.services.PastSessionsService;
import com.codeaddi.row_your_boat.controller.services.RowerService;
import com.codeaddi.row_your_boat.controller.services.SessionsService;
import com.codeaddi.row_your_boat.controller.util.DateUtil;
import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailability;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.http.inbound.RowingSession;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ViewService {

  @Autowired private SchedulerClient schedulerClient;
  @Autowired private AvailabilityClient availabilityClient;
  @Autowired private RowerClient rowerClient;

  public List<RowingSession> getAllSessions() {
    List<RowingSession> sessionsToReturn = schedulerClient.getAllSessions();
    return sortSessionsByStartTime(sessionsToReturn);
  }

  public Map<Squad, List<RowingSessions>> getAllStandardSessionsToDisplay() {
    List<RowingSession> rowingSessions = schedulerClient.getAllSessions();
    List<RowingSessions> groupedSessions = SessionsService.mapRowingSessionToSessions(rowingSessions);
    return sortSessionsBySquadAndDay(SessionsService.getRowingSessionsPerSquad(groupedSessions));
  }

  public Long getMaxId() {
    return schedulerClient.getAllSessions().stream()
            .max(Comparator.comparingLong(RowingSession::getId))
            .map(RowingSession::getId)
            .orElse(0L);
  }

  public Map<Squad, List<UpcomingSessionAvailabilityDTO>> getAvailabilitySessions() {
    List<UpcomingSessionAvailabilityDTO> upcomingSessions = availabilityClient.getAllUpcomingSessions();
    List<UpcomingSessionAvailabilityDTO> sessionsWithDays = AvailabilityService.addWeekday(upcomingSessions);
    return sortAvailabilitySessionsByDate(AvailabilityService.mapUpcomingSessionsToSquads(sessionsWithDays));
  }

  public Map<Squad, List<UpcomingSessionAvailabilityDTO>> addAvailabilityForThisUser(
          Long rowerId, Squad rowerSquad, Map<Squad, List<UpcomingSessionAvailabilityDTO>> allUpcomingSessions) {

    List<UpcomingSessionAvailability> rowersUpcomingAvailability = availabilityClient.getUpcomingAvailabilityForRower(rowerId);
    List<Long> rowersAvailableSessions = rowersUpcomingAvailability.stream()
            .map(UpcomingSessionAvailability::getUpcomingSessionId)
            .toList();

    List<UpcomingSessionAvailabilityDTO> updatedSessionsForSquad = updateRowerAvailabilityForSquad(
            allUpcomingSessions.get(rowerSquad), rowersAvailableSessions);

    allUpcomingSessions.replace(rowerSquad, updatedSessionsForSquad);
    return allUpcomingSessions;
  }

  public List<String> getAllPastSessionsDates() {
    return PastSessionsService.getUpcomingSessionDates(availabilityClient.getAllUpcomingPastSessions());
  }

  public List<String> getAllAvailableRowersForDate(String formattedDate) {
    Long upcomingSessionId = getSessionIdByDate(DateUtil.getDateFromFormattedString(formattedDate));

    List<Long> availableRowerIds = PastSessionsService.getRowersAvailableForSession(upcomingSessionId, availabilityClient.getAllUpcomingPastSessionAvailability());
    return RowerService.getNamesByIDs(availableRowerIds, rowerClient.getAllRowers());
  }

  private List<RowingSession> sortSessionsByStartTime(List<RowingSession> sessions) {
    sessions.sort(Comparator.comparing(RowingSession::getStartTime));
    return sessions;
  }

  private Map<Squad, List<RowingSessions>> sortSessionsBySquadAndDay(Map<Squad, List<RowingSessions>> sessions) {
    sessions.forEach((key, value) -> {
      value.sort(Comparator.comparing(RowingSessions::getStartTime));
      value.sort(Comparator.comparing(session -> session.getDay().ordinal()));
    });
    return sessions;
  }

  private Map<Squad, List<UpcomingSessionAvailabilityDTO>> sortAvailabilitySessionsByDate(
          Map<Squad, List<UpcomingSessionAvailabilityDTO>> sessions) {
    sessions.forEach((key, value) -> value.sort(Comparator.comparing(UpcomingSessionAvailabilityDTO::getDate)));
    return sessions;
  }

  private List<UpcomingSessionAvailabilityDTO> updateRowerAvailabilityForSquad(
          List<UpcomingSessionAvailabilityDTO> squadSessions, List<Long> availableSessionIds) {

    List<UpcomingSessionAvailabilityDTO> updatedSessions = new ArrayList<>();
    for (UpcomingSessionAvailabilityDTO session : squadSessions) {
      if (availableSessionIds.contains(session.getUpcomingSessionId())) {
        session.setRowerIsAvailable(true);
      }
      updatedSessions.add(session);
    }
    return updatedSessions;
  }

  private Long getSessionIdByDate(Date date) {
    return  AvailabilityService.getSessionIdByDate(date, availabilityClient.getAllUpcomingPastSessions());
  }
}
