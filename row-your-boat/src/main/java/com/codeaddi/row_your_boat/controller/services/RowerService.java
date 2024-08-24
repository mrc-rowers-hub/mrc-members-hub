package com.codeaddi.row_your_boat.controller.services;

import com.codeaddi.row_your_boat.model.http.inbound.Rower;
import java.util.ArrayList;
import java.util.List;

public class RowerService {

  public static List<String> getNamesByIDs(List<Long> ids, List<Rower> rowers) {
    List<String> names = new ArrayList<>();
    for (Rower rower : rowers) {
      if (ids.contains(rower.getRowerId())) {
        names.add(rower.getName());
      }
    }
    return names;
  }
}
