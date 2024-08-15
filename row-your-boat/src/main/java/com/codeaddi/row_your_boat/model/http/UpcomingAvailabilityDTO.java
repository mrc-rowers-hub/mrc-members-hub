package com.codeaddi.row_your_boat.model.http;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.Squad;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalTime;
import java.util.Date;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class UpcomingAvailabilityDTO {

    @JsonProperty("upcoming_session_id")
    private Long upcomingSessionId;

    private Date date;

    @JsonProperty("start_time")
    private LocalTime startTime;

    @JsonProperty("end_time")
    private LocalTime endTime;

    private Squad squad;

    private RowerLevel level;

    @JsonProperty("session_type")
    private SessionType sessionType;

}
