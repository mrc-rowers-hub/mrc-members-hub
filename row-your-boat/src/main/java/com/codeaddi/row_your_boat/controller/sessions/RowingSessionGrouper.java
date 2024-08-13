package com.codeaddi.row_your_boat.controller.sessions;

import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RowingSessionGrouper {

    public static Map<RowingSessionKey, List<RowingSession>> groupSessions(List<RowingSession> sessions) {
        return sessions.stream()
                .collect(Collectors.groupingBy(session -> new RowingSessionKey(session))); // Corrected line
    }

    @Getter
    static class RowingSessionKey {
        private final String day;
        private final String startTime;
        private final String endTime;
        private final SessionType sessionType;

        public RowingSessionKey(RowingSession session) {
            this.day = session.getDay();
            this.startTime = session.getStartTime();
            this.endTime = session.getEndTime();
            this.sessionType = session.getSessionType();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RowingSessionKey that = (RowingSessionKey) o;
            return Objects.equals(day, that.day) &&
                    Objects.equals(startTime, that.startTime) &&
                    Objects.equals(endTime, that.endTime) &&
                    sessionType == that.sessionType;
        }

        @Override
        public int hashCode() {
            return Objects.hash(day, startTime, endTime, sessionType);
        }
    }
}