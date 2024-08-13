package com.codeaddi.row_your_boat;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
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
            .build();

    public static RowingSession session2 = RowingSession.builder()
            .day("MONDAY")
            .startTime("18:00:00")
            .endTime("20:00:00")
            .sessionType(SessionType.WATER)
            .level(RowerLevel.NOVICE)
            .build();

    public static RowingSession session3 = RowingSession.builder()
            .day("MONDAY")
            .startTime("18:00:00")
            .endTime("20:00:00")
            .sessionType(SessionType.ERG)
            .level(RowerLevel.DEVELOPMENT)
            .build();
    public static List<RowingSession> sessions = Arrays.asList(session1, session2, session3);

}
