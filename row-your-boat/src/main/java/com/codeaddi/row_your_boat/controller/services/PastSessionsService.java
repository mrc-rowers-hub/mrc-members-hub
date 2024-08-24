package com.codeaddi.row_your_boat.controller.services;

import com.codeaddi.row_your_boat.controller.util.DateUtil;
import com.codeaddi.row_your_boat.model.http.inbound.PastSession;
import com.codeaddi.row_your_boat.model.http.inbound.PastSessionAvailability;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PastSessionsService {

  public static List<String> getUpcomingSessionDates(List<PastSession> sessions) {
    List<String> formattedDates = new ArrayList<>();
    for (Date date : sessions.stream().map(PastSession::getDate).toList()) {
      formattedDates.add(DateUtil.formatDate(date));
    }
    return formattedDates;
  }

  public static List<Long> getRowersAvailableForSession(
      Long upcomingSessionId, List<PastSessionAvailability> pastSessionAvailabilities) {
    List<PastSessionAvailability> rowersAvailable =
        pastSessionAvailabilities.stream()
            .filter(availability -> availability.getUpcomingSessionId().equals(upcomingSessionId))
            .toList();

    return rowersAvailable.stream().map(PastSessionAvailability::getRowerId).toList();
  }
}
