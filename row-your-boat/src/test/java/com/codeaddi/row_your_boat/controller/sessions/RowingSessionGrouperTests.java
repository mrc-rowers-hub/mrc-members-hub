package com.codeaddi.row_your_boat.controller.sessions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeaddi.row_your_boat.TestData;
import com.codeaddi.row_your_boat.controller.services.RowingSessionGrouper;
import com.codeaddi.row_your_boat.model.http.inbound.RowingSession;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class RowingSessionGrouperTests {
  @Test
  public void groupSessions_withMatchingSessions_groupsThem() {

    Map<RowingSessionGrouper.RowingSessionKey, List<RowingSession>> actualGroupedSessions =
        RowingSessionGrouper.groupSessions(TestData.StandardSessions.sessions);

    RowingSessionGrouper.RowingSessionKey expectedRowingSessionKeyFor1And2 =
        new RowingSessionGrouper.RowingSessionKey(TestData.StandardSessions.session1);

    RowingSessionGrouper.RowingSessionKey expectedRowingSessionKeyFor3 =
        new RowingSessionGrouper.RowingSessionKey(TestData.StandardSessions.session3);

    assertEquals(2, actualGroupedSessions.size());
    actualGroupedSessions.containsKey(expectedRowingSessionKeyFor1And2);
    actualGroupedSessions.containsKey(expectedRowingSessionKeyFor3);

    List<RowingSession> actualGroupedSession1And2 =
        actualGroupedSessions.get(expectedRowingSessionKeyFor1And2);
    assertTrue(
        actualGroupedSession1And2.contains(TestData.StandardSessions.session1)
            && actualGroupedSession1And2.contains(TestData.StandardSessions.session2));

    List<RowingSession> actualGroupedSession3 =
        actualGroupedSessions.get(expectedRowingSessionKeyFor3);
    assertTrue(actualGroupedSession3.contains(TestData.StandardSessions.session3));
  }

  @Test
  public void groupSessions_sameSessionDifferentSquads_NotGrouped() {

    Map<RowingSessionGrouper.RowingSessionKey, List<RowingSession>> actualGroupedSessions =
        RowingSessionGrouper.groupSessions(TestData.StandardSessions.sameSessionsMenWomen);

    RowingSessionGrouper.RowingSessionKey expectedRowingSessionKeyFor2 =
        new RowingSessionGrouper.RowingSessionKey(TestData.StandardSessions.session2);

    RowingSessionGrouper.RowingSessionKey expectedRowingSessionKeyFor4 =
        new RowingSessionGrouper.RowingSessionKey(TestData.StandardSessions.session4);

    assertEquals(2, actualGroupedSessions.size());
    actualGroupedSessions.containsKey(expectedRowingSessionKeyFor2);
    actualGroupedSessions.containsKey(expectedRowingSessionKeyFor4);

    List<RowingSession> actualGroupedSession1And2 =
        actualGroupedSessions.get(expectedRowingSessionKeyFor2);
    assertTrue(actualGroupedSession1And2.contains(TestData.StandardSessions.session2));

    List<RowingSession> actualGroupedSession3 =
        actualGroupedSessions.get(expectedRowingSessionKeyFor4);
    assertTrue(actualGroupedSession3.contains(TestData.StandardSessions.session4));
  }
}
