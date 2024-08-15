package com.codeaddi.row_your_boat.model.availability;

import com.codeaddi.row_your_boat.controller.sessions.UpcomingSessionsGrouper.*;
import com.codeaddi.row_your_boat.controller.sessions.UpcomingSessionsGrouper;
import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.Squad;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AvailabilityGroup {

    private String dayOfTheWeek;
    private  String date;
    private  String startTime;
    private  String endTime;
    private  SessionType sessionType;
    private  Squad squad;

    private  UpcomingSessionKey upcomingSessionKey;
    private  List<RowerLevel> levels;
    private  List<Long> upcomingSessionIds;

}
