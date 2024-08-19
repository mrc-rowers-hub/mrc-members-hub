package com.codeaddi.row_your_boat.controller.sessions;

import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.Weekday;
import com.codeaddi.row_your_boat.model.http.UpcomingAvailabilityDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AvailabilityService {

  public static List<UpcomingAvailabilityDTO> addWeekday(
      List<UpcomingAvailabilityDTO> upcomingAvailabilityDTOS) {
    List<UpcomingAvailabilityDTO> dtosWithWeekdays = new ArrayList<>();

    for (UpcomingAvailabilityDTO upcomingAvailabilityDTO : upcomingAvailabilityDTOS) {
      Weekday weekday = getDayOfTheWeekAsEnum(upcomingAvailabilityDTO.getDate());

      dtosWithWeekdays.add(
          UpcomingAvailabilityDTO.builder()
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

  public static Map<Squad, List<UpcomingAvailabilityDTO>> mapUpcomingSessionsToSquads(
      List<UpcomingAvailabilityDTO> upcomingSessionKeyListMap) {

    return upcomingSessionKeyListMap.stream()
        .collect(Collectors.groupingBy(UpcomingAvailabilityDTO::getSquad));
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
