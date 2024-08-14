package com.codeaddi.row_your_boat.model.sessions;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.SessionType;
import com.codeaddi.row_your_boat.model.Squad;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.List;

@Builder
@EqualsAndHashCode
@Value
@ToString
public class RowingSessions {
    private String day;

    private String startTime;

    private String endTime;

    private Squad squads;
    private List<RowerLevel> levels;
    private SessionType sessionType;
}
