package com.codeaddi.row_your_boat.model.http.enums;

import lombok.Getter;

@Getter
public enum Resource {
  SESSION_AVAILABILITY("session_availability/"),
  STANDARD_SESSIONS("standard_sessions/");

  private String endpoint;

  Resource(String s) {
    this.endpoint = s;
  }
}
