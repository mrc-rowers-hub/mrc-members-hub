package com.codeaddi.row_your_boat.controller.services;

import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.enums.Weekday;
import com.codeaddi.row_your_boat.model.http.UpcomingSessionAvailabilityDTO;
import com.codeaddi.row_your_boat.model.http.inbound.PastSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AvailabilityService {

  public static List<UpcomingSessionAvailabilityDTO> addWeekday(
      List<UpcomingSessionAvailabilityDTO> upcomingAvailabilityDTOS) {
    List<UpcomingSessionAvailabilityDTO> dtosWithWeekdays = new ArrayList<>();

    for (UpcomingSessionAvailabilityDTO upcomingAvailabilityDTO : upcomingAvailabilityDTOS) {
      Weekday weekday = getDayOfTheWeekAsEnum(upcomingAvailabilityDTO.getDate());

      dtosWithWeekdays.add(
          UpcomingSessionAvailabilityDTO.builder()
              .weekday(weekday)
              .level(upcomingAvailabilityDTO.getLevel())
              .upcomingSessionId(upcomingAvailabilityDTO.getUpcomingSessionId())
              .startTime(upcomingAvailabilityDTO.getStartTime())
              .endTime(upcomingAvailabilityDTO.getEndTime())
              .date(upcomingAvailabilityDTO.getDate())
              .squad(upcomingAvailabilityDTO.getSquad())
              .sessionType(upcomingAvailabilityDTO.getSessionType())
              .build());
    }
    return dtosWithWeekdays;
  }

  public static Map<Squad, List<UpcomingSessionAvailabilityDTO>> mapUpcomingSessionsToSquads(
      List<UpcomingSessionAvailabilityDTO> upcomingSessionKeyListMap) {

    return upcomingSessionKeyListMap.stream()
        .collect(Collectors.groupingBy(UpcomingSessionAvailabilityDTO::getSquad));
  }

  public static Long getSessionIdByDate(Date date, List<PastSession> pastSessions) {
    return pastSessions.stream()
        .filter(session -> session.getDate().equals(date))
        .findAny()
        .map(PastSession::getUpcomingSessionId)
        .orElseThrow(() -> new NoSuchElementException("No session found for the provided date"));
  }

  private static String getDayOfTheWeek(String dateAsString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date;
    date = LocalDate.parse(dateAsString, formatter);
    return date.getDayOfWeek().toString();
  }

  private static Weekday getDayOfTheWeekAsEnum(String dateAsString) {
    return Weekday.fromString(getDayOfTheWeek(dateAsString));
  }
}
