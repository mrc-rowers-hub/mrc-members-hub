package com.codeaddi.row_your_boat.model.http;

import lombok.*;

@Getter
@Builder
public class UpcomingSessionAvailability {
   private Long id;
 private Long upcomingSessionId;
    private Long rowerId;
}
