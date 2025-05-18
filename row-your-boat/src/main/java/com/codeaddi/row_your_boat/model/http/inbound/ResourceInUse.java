package com.codeaddi.row_your_boat.model.http.inbound;

import com.codeaddi.row_your_boat.model.http.enums.resources.EquipmentType;
import java.time.LocalTime;
import java.util.Date;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceInUse {

    private Long id;
    private Long resource_id; // for the boat, or blade
    private EquipmentType equipmentType;
    private Integer quantity;
    private Long upcomingSessionId;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;
}
