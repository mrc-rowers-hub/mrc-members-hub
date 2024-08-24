package com.codeaddi.row_your_boat.controller.services;

import com.codeaddi.row_your_boat.model.enums.SessionType;
import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.http.inbound.RowingSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Getter;

public class RowingSessionGrouper {

  public static Map<RowingSessionKey, List<RowingSession>> groupSessions(
      List<RowingSession> sessions) {
    return sessions.stream()
        .collect(Collectors.groupingBy(session -> new RowingSessionKey(session)));
  }

  @Getter
  public static class RowingSessionKey {
    private final String day;
    private final String startTime;
    private final String endTime;
    private final SessionType sessionType;
    private final Squad squad;

    public RowingSessionKey(RowingSession session) {
      this.day = session.getDay();
      this.startTime = session.getStartTime();
      this.endTime = session.getEndTime();
      this.sessionType = session.getSessionType();
      this.squad = session.getSquad();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      RowingSessionKey that = (RowingSessionKey) o;
      return Objects.equals(day, that.day)
          && Objects.equals(startTime, that.startTime)
          && Objects.equals(endTime, that.endTime)
          && squad == that.squad
          && sessionType == that.sessionType;
    }

    @Override
    public int hashCode() {
      return Objects.hash(day, startTime, endTime, sessionType, squad);
    }
  }
}
