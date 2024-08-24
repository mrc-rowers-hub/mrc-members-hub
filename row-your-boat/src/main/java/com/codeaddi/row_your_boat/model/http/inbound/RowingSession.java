package com.codeaddi.row_your_boat.model.http.inbound;

import com.codeaddi.row_your_boat.model.enums.RowerLevel;
import com.codeaddi.row_your_boat.model.enums.SessionType;
import com.codeaddi.row_your_boat.model.enums.Squad;
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
