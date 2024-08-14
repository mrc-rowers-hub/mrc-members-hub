package com.codeaddi.row_your_boat.controller.sessions;

import com.codeaddi.row_your_boat.TestData;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class SessionsServiceTests {
    @Test
    public void mapRowingSessionToSessions_withMultipleSessions_groupsSessionsByTime() {

        List<RowingSessions> actualRowingSessions = SessionsService.mapRowingSessionToSessions(TestData.sessions);
        List<RowingSessions> expectedRowingSessions = TestData.groupedSessions;

        for(RowingSessions rowingSessions : actualRowingSessions){
            System.out.println(rowingSessions.toString());
        }

        assertEquals(expectedRowingSessions.size(), actualRowingSessions.size());

//        Todo complete the assertions to check fields

    }

    @Test
    public void getRowingSessionsPerSquad_withTwoSquads_groupsBySquad() {

        List<Squad> expectedSquads = new ArrayList<>();
        for(RowingSession rowingSession : TestData.sessions){
            if(!expectedSquads.contains(rowingSession.getSquad())){
                expectedSquads.add(rowingSession.getSquad());

            }
        }

        List<RowingSessions> actualRowingSessions = SessionsService.mapRowingSessionToSessions(TestData.sessions);
        Map<Squad, List<RowingSessions>> actualMap = SessionsService.getRowingSessionsPerSquad(actualRowingSessions);

        List<Squad> actualSquads = actualMap.keySet().stream().toList();

        assertArrayEquals(new List[]{expectedSquads}, new List[]{actualSquads});

    }

}
