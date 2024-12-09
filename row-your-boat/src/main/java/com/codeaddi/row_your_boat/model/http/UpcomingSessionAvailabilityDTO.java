package com.codeaddi.row_your_boat.model.http;

import com.codeaddi.row_your_boat.model.enums.RowerLevel;
import com.codeaddi.row_your_boat.model.enums.SessionType;
import com.codeaddi.row_your_boat.model.enums.Squad;
import com.codeaddi.row_your_boat.model.enums.Weekday;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@AllArgsConstructor
@Jacksonized
public class UpcomingSessionAvailabilityDTO { // todo could this be, just session availabilityDTO?

  private Weekday weekday;

  @JsonProperty("upcoming_session_id")
  private Long upcomingSessionId;

  private String date;

  @JsonProperty("start_time")
  private String startTime;

  @JsonProperty("end_time")
  private String endTime;

  private Squad squad;

  private RowerLevel level;

  @JsonProperty("session_type")
  private SessionType sessionType;

  @Setter private Boolean rowerIsAvailable;
}
