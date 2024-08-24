package com.codeaddi.row_your_boat.model.rowers;

import com.codeaddi.row_your_boat.model.RowerLevel;
import com.codeaddi.row_your_boat.model.Squad;
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
