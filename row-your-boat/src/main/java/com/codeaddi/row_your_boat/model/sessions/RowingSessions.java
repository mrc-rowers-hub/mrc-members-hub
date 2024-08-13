package com.codeaddi.row_your_boat.model.sessions;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.Squad;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalTime;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class RowingSessions {

  @JsonProperty("session_id")
  private Long id;

  private String day;

  @JsonProperty("start_time")
  private LocalTime startTime;

  @JsonProperty("end_time")
  private LocalTime endTime;

  private Squad squad;

  private RowerLevel level;

  @JsonProperty("session_type")
  private SessionType sessionType;
}
