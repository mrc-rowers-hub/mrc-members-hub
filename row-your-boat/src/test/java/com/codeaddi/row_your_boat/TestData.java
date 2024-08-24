package com.codeaddi.row_your_boat;

import com.codeaddi.row_your_boat.model.enums.RowerLevel;
import com.codeaddi.row_your_boat.model.enums.SessionType;
import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.enums.Weekday;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.inbound.PastSession;
import com.codeaddi.row_your_boat.model.http.inbound.PastSessionAvailability;
import com.codeaddi.row_your_boat.model.http.inbound.Rower;
import com.codeaddi.row_your_boat.model.sessions.RowingSessions;
import com.codeaddi.row_your_boat.model.http.inbound.RowingSession;
import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestData {

  public class StandardSessions {
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
    public static List<RowingSession> sessions = Arrays.asList(session1, session2, session3);
    public static List<RowingSession> sameSessionsMenWomen = Arrays.asList(session2, session4);
    public static List<RowingSessions> groupedSessions = Arrays.asList(session1And2, session3s);
  }


  // Create sample data
  public static UpcomingSessionAvailabilityDTO upcomingSession1 =
      UpcomingSessionAvailabilityDTO.builder()
          .upcomingSessionId(1L)
          .date("2024-08-16")
          .startTime(LocalTime.of(10, 0).toString())
          .endTime(LocalTime.of(11, 0).toString())
          .squad(Squad.MENS)
          .level(RowerLevel.NOVICE)
          .sessionType(SessionType.WATER)
          .build();
  public static UpcomingSessionAvailabilityDTO upcomingSession2 =
      UpcomingSessionAvailabilityDTO.builder()
          .upcomingSessionId(2L)
          .date("2024-08-16")
          .startTime(LocalTime.of(10, 0).toString())
          .endTime(LocalTime.of(11, 0).toString())
          .squad(Squad.MENS)
          .level(RowerLevel.NOVICE)
          .sessionType(SessionType.WATER)
          .build();
  public static UpcomingSessionAvailabilityDTO upcomingSession3 =
      UpcomingSessionAvailabilityDTO.builder()
          .upcomingSessionId(3L)
          .date("2024-08-17")
          .startTime(LocalTime.of(9, 0).toString())
          .endTime(LocalTime.of(10, 0).toString())
          .squad(Squad.DEVELOPMENT)
          .level(RowerLevel.DEVELOPMENT)
          .sessionType(SessionType.ERG)
          .build();






  public static String formattedDate = "Mon Nov 11 2024 00:00";

  public static PastSession pastSession1 = TestUtil.createPastSession(formattedDate);
  public static PastSession pastSession2 =
      PastSession.builder()
          .sessionId(2L)
          .upcomingSessionId(3L)
          .date(Date.from(Instant.now()))
          .build();

  public static PastSessionAvailability pastSessionAvailability1 =
      PastSessionAvailability.builder().upcomingSessionId(1L).rowerId(1L).build();
  public static PastSessionAvailability pastSessionAvailability3 =
      PastSessionAvailability.builder().upcomingSessionId(1L).rowerId(2L).build();
  public static PastSessionAvailability pastSessionAvailability2 =
      PastSessionAvailability.builder().upcomingSessionId(2L).rowerId(1L).build();

  public static Rower rower1 =
      Rower.builder()
          .rowerId(1L)
          .name("rower 1")
          .squad(Squad.WOMENS)
          .level(RowerLevel.INTERMEDIATE)
          .build();
  public static Rower rower2 =
      Rower.builder()
          .rowerId(2L)
          .name("rower 2")
          .squad(Squad.WOMENS)
          .level(RowerLevel.INTERMEDIATE)
          .build();
  public static Rower rower3 =
      Rower.builder()
          .rowerId(3L)
          .name("rower 2")
          .squad(Squad.WOMENS)
          .level(RowerLevel.INTERMEDIATE)
          .build();

}
