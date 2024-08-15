package com.codeaddi.row_your_boat.model.availability;

import com.codeaddi.row_your_boat.controller.sessions.UpcomingSessionsGrouper.*;
import com.codeaddi.row_your_boat.controller.sessions.UpcomingSessionsGrouper;
import com.codeaddi.row_your_boat.model.RowerLevel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AvailabilityGroup {
    UpcomingSessionKey upcomingSessionKey;
    List<RowerLevel> levels;
    List<Long> upcomingSessionIds;
}
