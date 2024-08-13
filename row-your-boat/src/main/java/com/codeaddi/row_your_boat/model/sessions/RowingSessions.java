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

  private Long id;

  private String day;

  private String startTime;

  private String endTime;

  private Squad squad;

  private RowerLevel level;

  private SessionType sessionType;
}
