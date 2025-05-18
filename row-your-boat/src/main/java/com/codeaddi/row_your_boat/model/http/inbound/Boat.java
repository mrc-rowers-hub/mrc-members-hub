package com.codeaddi.row_your_boat.model.http.inbound;

import com.codeaddi.row_your_boat.model.http.enums.resources.*;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Boat {
    private Long id;
    private String name;
    private float avgCrewWeight;
    private BoatType boatType;
    private RowerLevel minimumRowerLevel;
    private EquipmentStatus status;
    private Long bestBladesId;

    public int getCapacity(){
        return this.boatType.getCapacity();
    }
}
