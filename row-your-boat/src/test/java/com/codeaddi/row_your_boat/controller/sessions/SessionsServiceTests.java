package com.codeaddi.row_your_boat.controller.sessions;

import static org.junit.jupiter.api.Assertions.*;

import com.codeaddi.row_your_boat.TestData;
import com.codeaddi.row_your_boat.controller.services.SessionsService;
import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.http.inbound.RowingSession;
import java.util.*;
import org.junit.jupiter.api.Test;

public class SessionsServiceTests {
  @Test
  public void mapRowingSessionToSessions_withMultipleSessions_groupsSessionsByTime() {

    List<RowingSessions> actualRowingSessions =
        SessionsService.mapRowingSessionToSessions(TestData.StandardSessions.sessions);
    List<RowingSessions> expectedRowingSessions = TestData.StandardSessions.groupedSessions;

    for (RowingSessions rowingSessions : actualRowingSessions) {
      System.out.println(rowingSessions.toString());
    }

    assertEquals(expectedRowingSessions.size(), actualRowingSessions.size());

    //        Todo complete the assertions to check fields

  }

  @Test
  public void getRowingSessionsPerSquad_withTwoSquads_groupsBySquad() {

    List<Squad> expectedSquads = new ArrayList<>();
    for (RowingSession rowingSession : TestData.StandardSessions.sessions) {
      if (!expectedSquads.contains(rowingSession.getSquad())) {
        expectedSquads.add(rowingSession.getSquad());
      }
    }
    Collections.sort(expectedSquads);


    List<RowingSessions> actualRowingSessions =
        SessionsService.mapRowingSessionToSessions(TestData.StandardSessions.sessions);
    Map<Squad, List<RowingSessions>> actualMap =
        SessionsService.getRowingSessionsPerSquad(actualRowingSessions);

    List<Squad> actualSquads = actualMap.keySet().stream().sorted().toList();

    assertArrayEquals(new List[] {expectedSquads}, new List[] {actualSquads});
  }
}
