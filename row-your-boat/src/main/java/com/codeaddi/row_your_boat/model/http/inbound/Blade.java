package com.codeaddi.row_your_boat.model.http.inbound;

import lombok.*;
import com.codeaddi.row_your_boat.model.http.enums.resources.EquipmentStatus;

@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blade {
    private Long id;
    private String name;
    private int amount;
    private EquipmentStatus status;
}

