package com.codeaddi.row_your_boat.controller.sessions;

import com.codeaddi.row_your_boat.TestData;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
