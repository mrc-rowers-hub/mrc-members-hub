package com.codeaddi.row_your_boat.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Weekday {
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY,
  SUNDAY;

  public static Weekday fromString(String string){
    try{
      return Weekday.valueOf(string.toUpperCase());
    } catch(IllegalArgumentException e){
      log.error("No weekday found for: {}", string);
      throw e;

    }
  }
}
