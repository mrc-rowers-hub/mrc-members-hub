package com.codeaddi.row_your_boat.model.http.inbound;

import com.codeaddi.row_your_boat.model.enums.RowerLevel;
import com.codeaddi.row_your_boat.model.enums.Squad;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rower {
  private Long rowerId;
  private String name;
  private Squad squad;
  private RowerLevel level;
}
