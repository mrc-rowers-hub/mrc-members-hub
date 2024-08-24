package com.codeaddi.row_your_boat.view.display;

import com.codeaddi.row_your_boat.controller.http.AvailabilityClient;
import com.codeaddi.row_your_boat.controller.http.RowerClient;
import com.codeaddi.row_your_boat.controller.http.SchedulerClient;
import com.codeaddi.row_your_boat.controller.sessions.AvailabilityService;
import com.codeaddi.row_your_boat.controller.sessions.PastSessionsService;
import com.codeaddi.row_your_boat.controller.sessions.RowerService;
import com.codeaddi.row_your_boat.controller.sessions.SessionsService;
import com.codeaddi.row_your_boat.controller.util.DateUtil;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailability;
import com.codeaddi.row_your_boat.model.http.inbound.PastSession;
import com.codeaddi.row_your_boat.model.http.inbound.PastSessionAvailability;
import com.codeaddi.row_your_boat.model.rowers.Rower;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ViewService {
  // Todo remove the availability group key bits

  @Autowired private SchedulerClient schedulerClient;
  @Autowired private AvailabilityClient availabilityClient;
  @Autowired private RowerClient rowerClient;

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

    standardSessions.forEach(
        (key, value) -> value.sort(Comparator.comparing(RowingSessions::getStartTime)));

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

  public Map<Squad, List<UpcomingAvailabilityDTO>> getAvailabilitySessions() {
    List<UpcomingAvailabilityDTO> upcomingSessions = availabilityClient.getAllUpcomingSessions();

    List<UpcomingAvailabilityDTO> sessionsWithDays =
        AvailabilityService.addWeekday(upcomingSessions);

    Map<Squad, List<UpcomingAvailabilityDTO>> toReturn =
        AvailabilityService.mapUpcomingSessionsToSquads(sessionsWithDays);

    toReturn.forEach(
        (key, value) -> value.sort(Comparator.comparing(UpcomingAvailabilityDTO::getDate)));

    return toReturn;
  }

  // Todo should this live in viewService?
  public Map<Squad, List<UpcomingAvailabilityDTO>> addAvailabilityForThisUser(
      Long rowerId,
      Squad rowerSquad,
      Map<Squad, List<UpcomingAvailabilityDTO>> allUpcomingSessions) {
    List<UpcomingSessionAvailability> rowersUpcomingAvailability =
        availabilityClient.getUpcomingAvailabilityForRower(rowerId);
    List<Long> rowersAvailableSessions =
        rowersUpcomingAvailability.stream()
            .map(UpcomingSessionAvailability::getUpcomingSessionId)
            .toList();

    List<UpcomingAvailabilityDTO> upcomingSessionsForThisSquad =
        allUpcomingSessions.get(rowerSquad);
    List<UpcomingAvailabilityDTO> upcomingSessionsForThisSquadWithAvailability = new ArrayList<>();

    for (UpcomingAvailabilityDTO upcomingAvailabilityDTO : upcomingSessionsForThisSquad) {
      if (rowersAvailableSessions.contains(upcomingAvailabilityDTO.getUpcomingSessionId())) {
        upcomingAvailabilityDTO.setRowerIsAvailable(true);
      }

      upcomingSessionsForThisSquadWithAvailability.add(upcomingAvailabilityDTO);
    }

    allUpcomingSessions.replace(rowerSquad, upcomingSessionsForThisSquadWithAvailability);

    return allUpcomingSessions;
  }

  public List<String> getAllPastSessionsDates() {
    List<PastSession> upcomingPastSessions = availabilityClient.getAllUpcomingPastSessions();
    return PastSessionsService.getUpcomingSessionDates(upcomingPastSessions);
  }

  public List<String> getAllAvailableRowersForDate(String formattedDate) {
    Date date = DateUtil.getDateFromFormattedString(formattedDate);
    List<PastSession> upcomingPastSessions = availabilityClient.getAllUpcomingPastSessions();
    Long upcomingSessionId =
        upcomingPastSessions.stream()
            .filter(session -> session.getDate().equals(date))
            .findAny()
            .get()
            .getUpcomingSessionId();

    List<PastSessionAvailability> rowersAvailable =
        availabilityClient.getAllUpcomingPastSessionAvailability().stream()
            .filter(availability -> availability.getUpcomingSessionId().equals(upcomingSessionId))
            .toList();
    List<Long> availableRowers =
        rowersAvailable.stream().map(PastSessionAvailability::getRowerId).toList();
    List<Rower> allRowers = rowerClient.getAllRowers();

    return RowerService.getNamesByIDs(availableRowers, allRowers);
  }
}
