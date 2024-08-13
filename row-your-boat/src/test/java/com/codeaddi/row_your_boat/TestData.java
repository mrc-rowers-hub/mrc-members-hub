package com.codeaddi.row_your_boat;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;

import java.util.Arrays;
import java.util.List;

public class TestData {

    public static RowingSession session1 = RowingSession.builder()
            .day("MONDAY")
            .startTime("18:00:00")
            .endTime("20:00:00")
            .sessionType(SessionType.WATER)
            .level(RowerLevel.INTERMEDIATE)
            .squad(Squad.WOMENS)
            .build();

    public static RowingSession session2 = RowingSession.builder()
            .day("MONDAY")
            .startTime("18:00:00")
            .endTime("20:00:00")
            .sessionType(SessionType.WATER)
            .level(RowerLevel.NOVICE)
            .squad(Squad.DEVELOPMENT)
            .build();

    public static RowingSession session3 = RowingSession.builder()
            .day("MONDAY")
            .startTime("18:00:00")
            .endTime("20:00:00")
            .sessionType(SessionType.ERG)
            .level(RowerLevel.DEVELOPMENT)
            .squad(Squad.DEVELOPMENT)
            .build();

    public static RowingSessions session1And2 = RowingSessions.builder().day("MONDAY")
            .startTime("18:00:00")
            .endTime("20:00:00")
            .sessionType(SessionType.WATER)
            .levels(List.of(RowerLevel.INTERMEDIATE, RowerLevel.INTERMEDIATE))
            .squads(List.of(Squad.DEVELOPMENT, Squad.WOMENS)).build();

    public static RowingSessions session3s = RowingSessions.builder().day("MONDAY")
            .startTime("18:00:00")
            .endTime("20:00:00")
            .sessionType(SessionType.ERG)
            .levels(List.of(RowerLevel.DEVELOPMENT))
            .squads(List.of(Squad.DEVELOPMENT)).build();

    public static List<RowingSession> sessions = Arrays.asList(session1, session2, session3);
    public static List<RowingSessions> groupedSessions = Arrays.asList(session1And2, session3s);


}
