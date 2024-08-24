package com.codeaddi.row_your_boat.model.http.inbound;

import lombok.*;


@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PastSessionAvailability {
    private Long id;
    private Long upcomingSessionId;
    private Long rowerId;
}
