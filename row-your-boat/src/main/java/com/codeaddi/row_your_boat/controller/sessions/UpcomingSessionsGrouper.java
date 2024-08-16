package com.codeaddi.row_your_boat.controller.sessions;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Getter;

public class UpcomingSessionsGrouper {

  public static Map<UpcomingSessionsGrouper.UpcomingSessionKey, List<UpcomingAvailabilityDTO>>
      groupSessions(List<UpcomingAvailabilityDTO> upcomingSession) {
    return upcomingSession.stream().collect(Collectors.groupingBy(UpcomingSessionKey::new));
  }

  @Getter
  public static class UpcomingSessionKey {
    private final String date;
    private final String startTime;
    private final String endTime;
    private final SessionType sessionType;
    private final Squad squad;
    private final RowerLevel rowerLevel;

    public UpcomingSessionKey(UpcomingAvailabilityDTO session) {
      this.date = session.getDate();
      this.startTime = session.getStartTime();
      this.endTime = session.getEndTime();
      this.sessionType = session.getSessionType();
      this.squad = session.getSquad();
      this.rowerLevel = session.getLevel();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      UpcomingSessionsGrouper.UpcomingSessionKey that =
          (UpcomingSessionsGrouper.UpcomingSessionKey) o;
      return Objects.equals(date, that.date)
          && Objects.equals(startTime, that.startTime)
          && Objects.equals(endTime, that.endTime)
          && squad == that.squad
          && rowerLevel == that.rowerLevel
          && sessionType == that.sessionType;
    }

    @Override
    public int hashCode() {
      return Objects.hash(date, startTime, endTime, sessionType, squad, rowerLevel);
    }
  }
}
