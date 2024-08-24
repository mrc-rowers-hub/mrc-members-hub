package com.codeaddi.row_your_boat;

import com.codeaddi.row_your_boat.model.http.inbound.PastSession;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestUtil {
  public static PastSession createPastSession(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm");
    LocalDate localDate = LocalDate.parse(dateString, formatter);

    LocalDateTime localDateTime = localDate.atTime(LocalTime.MIN);

    ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));

    Instant instant = zonedDateTime.toInstant();
    Date date = Date.from(instant);

    return PastSession.builder().date(date).upcomingSessionId(1L).sessionId(2L).build();
  }
}
