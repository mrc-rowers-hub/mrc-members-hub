package com.codeaddi.row_your_boat.model.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Weekday {
  MONDAY(1),
  TUESDAY(2),
  WEDNESDAY(3),
  THURSDAY(4),
  FRIDAY(5),
  SATURDAY(6),
  SUNDAY(7);

  private final int dayNumber;

  Weekday(int dayNumber) {
    this.dayNumber = dayNumber;
  }

  public static Weekday fromString(String string) {
    try {
      return Weekday.valueOf(string.toUpperCase());
    } catch (IllegalArgumentException e) {
      log.error("No weekday found for: {}", string);
      throw e;
    }
  }
}
