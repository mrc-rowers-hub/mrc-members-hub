package com.codeaddi.row_your_boat.model.http.inbound;

import java.util.Date;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PastSession {
  // a class for sessions after the
  // cutoff time for availability,
  //  not necessarily in the past

  private Long upcomingSessionId;
  private Long sessionId;
  private Date date;
}
