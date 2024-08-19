package com.codeaddi.row_your_boat.model.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AvailabilityDTO {
    private int sessionId;
    private boolean availability;
}
