package com.codeaddi.row_your_boat.model.http;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpcomingSessionAvailability {
   private Long id;
    private Long upcomingSessionId;
    private Long rowerId;
}
