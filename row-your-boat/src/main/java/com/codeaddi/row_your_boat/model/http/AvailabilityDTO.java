package com.codeaddi.row_your_boat.model.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString //remove me?
public class AvailabilityDTO {
    private String squadName;
    private String upcomingSessionId;
    private String availability;

}
