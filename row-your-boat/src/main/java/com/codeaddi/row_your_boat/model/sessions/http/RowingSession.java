package com.codeaddi.row_your_boat.model.sessions.http;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.Squad;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class RowingSession {

  private Long id;

  private String day;

  private String startTime;

  private String endTime;

  private Squad squad;

  private RowerLevel level;

  private SessionType sessionType;
}
