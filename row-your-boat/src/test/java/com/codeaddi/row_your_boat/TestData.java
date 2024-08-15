package com.codeaddi.row_your_boat;

import com.codeaddi.row_your_boat.controller.sessions.UpcomingSessionsGrouper;
import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.Weekday;
import com.codeaddi.row_your_boat.model.availability.AvailabilityGroup;
import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.sessions.http.RowingSession;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class TestData {

  public static RowingSession session1 =
      RowingSession.builder()
          .day("MONDAY")
          .startTime("18:00:00")
          .endTime("20:00:00")
          .sessionType(SessionType.WATER)
          .level(RowerLevel.INTERMEDIATE)
          .squad(Squad.WOMENS)
          .build();

  public static RowingSession session2 =
      RowingSession.builder()
          .day("MONDAY")
          .startTime("18:00:00")
          .endTime("20:00:00")
          .sessionType(SessionType.WATER)
          .level(RowerLevel.NOVICE)
          .squad(Squad.WOMENS)
          .build();

  public static RowingSession session4 =
      RowingSession.builder()
          .day("MONDAY")
          .startTime("18:00:00")
          .endTime("20:00:00")
          .sessionType(SessionType.WATER)
          .level(RowerLevel.NOVICE)
          .squad(Squad.MENS)
          .build();

  public static RowingSession session3 =
      RowingSession.builder()
          .day("MONDAY")
          .startTime("18:00:00")
          .endTime("20:00:00")
          .sessionType(SessionType.ERG)
          .level(RowerLevel.DEVELOPMENT)
          .squad(Squad.DEVELOPMENT)
          .build();

  public static RowingSessions session1And2 =
      RowingSessions.builder()
          .day(Weekday.MONDAY)
          .startTime("18:00:00")
          .endTime("20:00:00")
          .sessionType(SessionType.WATER)
          .levels(List.of(RowerLevel.NOVICE, RowerLevel.INTERMEDIATE))
          .squads(Squad.WOMENS)
          .build();

  public static RowingSessions session3s =
      RowingSessions.builder()
          .day(Weekday.MONDAY)
          .startTime("18:00:00")
          .endTime("20:00:00")
          .sessionType(SessionType.ERG)
          .levels(List.of(RowerLevel.DEVELOPMENT))
          .squads(Squad.DEVELOPMENT)
          .build();

  // Create sample data
  public static UpcomingAvailabilityDTO upcomingSession1 =
      UpcomingAvailabilityDTO.builder()
          .upcomingSessionId(1L)
          .date("2024-08-16")
          .startTime(LocalTime.of(10, 0).toString())
          .endTime(LocalTime.of(11, 0).toString())
          .squad(Squad.MENS)
          .level(RowerLevel.NOVICE)
          .sessionType(SessionType.WATER)
          .build();
  public static UpcomingAvailabilityDTO upcomingSession2 =
      UpcomingAvailabilityDTO.builder()
          .upcomingSessionId(2L)
          .date("2024-08-16")
          .startTime(LocalTime.of(10, 0).toString())
          .endTime(LocalTime.of(11, 0).toString())
          .squad(Squad.MENS)
          .level(RowerLevel.INTERMEDIATE)
          .sessionType(SessionType.WATER)
          .build();
  public static UpcomingAvailabilityDTO upcomingSession3 =
      UpcomingAvailabilityDTO.builder()
          .upcomingSessionId(3L)
          .date("2024-08-17")
          .startTime(LocalTime.of(9, 0).toString())
          .endTime(LocalTime.of(10, 0).toString())
          .squad(Squad.DEVELOPMENT)
          .level(RowerLevel.DEVELOPMENT)
          .sessionType(SessionType.ERG)
          .build();

  public static AvailabilityGroup availabilityGroup1 =
      AvailabilityGroup.builder()
          .upcomingSessionKey(new UpcomingSessionsGrouper.UpcomingSessionKey(upcomingSession1))
          .levels(List.of(RowerLevel.NOVICE, RowerLevel.INTERMEDIATE))
          .upcomingSessionIds(List.of(1L, 2L))
          .build();

  public static AvailabilityGroup availabilityGroup2 =
      AvailabilityGroup.builder()
          .upcomingSessionKey(new UpcomingSessionsGrouper.UpcomingSessionKey(upcomingSession3))
          .levels(List.of(RowerLevel.DEVELOPMENT))
          .upcomingSessionIds(List.of(3L))
          .build();

  public static List<UpcomingAvailabilityDTO> upcomingAvailabilityDTOS =
      List.of(upcomingSession1, upcomingSession2, upcomingSession3);
  public static List<RowingSession> sessions = Arrays.asList(session1, session2, session3);
  public static List<RowingSession> sameSessionsMenWomen = Arrays.asList(session2, session4);
  public static List<RowingSessions> groupedSessions = Arrays.asList(session1And2, session3s);
  public static List<AvailabilityGroup> availabilityGroups =
      Arrays.asList(availabilityGroup1, availabilityGroup2);
}
