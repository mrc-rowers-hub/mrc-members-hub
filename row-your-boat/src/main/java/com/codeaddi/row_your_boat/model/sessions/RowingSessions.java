package com.codeaddi.row_your_boat.model.sessions;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.Squad;
import com.codeaddi.row_your_boat.model.Weekday;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Builder
@EqualsAndHashCode
@Value
@ToString
public class RowingSessions {
  private Weekday day;

  private String startTime;

  private String endTime;

  private Squad squads;
  private List<RowerLevel> levels;
  private SessionType sessionType;
}
