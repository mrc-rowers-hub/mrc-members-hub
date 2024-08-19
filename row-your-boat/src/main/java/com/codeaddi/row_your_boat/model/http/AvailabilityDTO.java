package com.codeaddi.row_your_boat.model.http;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AvailabilityDTO {
//     todo add rowerId field
//     Todo update the fields to have json strings
    private String rowerId;
    private int sessionId;
    private boolean availability;
}
