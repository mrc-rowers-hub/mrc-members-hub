package com.codeaddi.row_your_boat.controller.services;

import com.codeaddi.row_your_boat.model.enums.RowerLevel;
import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.enums.Weekday;
import com.codeaddi.row_your_boat.model.http.inbound.RowingSession;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SessionsService {

  public static List<RowingSessions> mapRowingSessionToSessions(
      List<RowingSession> rowingSessionList) {
    Map<RowingSessionGrouper.RowingSessionKey, List<RowingSession>> sessionsWithSameTimes =
        RowingSessionGrouper.groupSessions(rowingSessionList);
    List<RowingSessions> rowingSessionsToReturn = new ArrayList<>();

    for (RowingSessionGrouper.RowingSessionKey key : sessionsWithSameTimes.keySet()) {
      List<RowerLevel> rowerLevels = new ArrayList<>();

      for (RowingSession rowingSession : sessionsWithSameTimes.get(key)) {

        if (!rowerLevels.contains(rowingSession.getLevel())) {
          rowerLevels.add(rowingSession.getLevel());
        }
      }

      rowingSessionsToReturn.add(
          RowingSessions.builder()
              .day(Weekday.fromString((key.getDay())))
              .sessionType(key.getSessionType())
              .startTime(key.getStartTime())
              .endTime(key.getEndTime())
              .squads(key.getSquad())
              .levels(rowerLevels)
              .build());
    }
    return rowingSessionsToReturn;
  }

  public static Map<Squad, List<RowingSessions>> getRowingSessionsPerSquad(
      List<RowingSessions> rowingSessions) {
    return rowingSessions.stream().collect(Collectors.groupingBy(RowingSessions::getSquads));
  }
}
