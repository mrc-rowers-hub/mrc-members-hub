package com.codeaddi.row_your_boat.model.http;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AvailabilityDTO {
  //     Todo update the fields to have json strings
  private Long rowerId;
  private int sessionId; // todo update to be upcoming session id & to be Long
  private boolean availability;
}
