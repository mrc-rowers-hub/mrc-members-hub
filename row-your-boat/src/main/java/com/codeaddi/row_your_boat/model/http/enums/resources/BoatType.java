package com.codeaddi.row_your_boat.model.http.enums.resources;

import lombok.Getter;

@Getter
public enum BoatType {
  SINGLE_SCULL(1),
  DOUBLE_SCULL(2),
  COXLESS_QUAD(4),
  COXED_QUAD(5),
  OCTUPLE(8),
  COXLESS_PAIR(2),
  COXLESS_FOUR(4),
  COXED_PAIR(3),
  COXED_FOUR(5),
  COXED_EIGHT(9);

  private final int capacity;

  BoatType(int capacity) {
    this.capacity = capacity;
  }

}

