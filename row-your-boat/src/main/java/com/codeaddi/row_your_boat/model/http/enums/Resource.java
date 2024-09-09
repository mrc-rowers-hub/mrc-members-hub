package com.codeaddi.row_your_boat.model.http.enums;

import lombok.Getter;

@Getter
public enum Resource {
  SESSION_AVAILABILITY("session_availability/", Service.SCHEDULER),
  STANDARD_SESSIONS("standard_sessions/",Service.SCHEDULER),
  ROWERS("rowers/", Service.SCHEDULER);

  private String endpoint;
  private Service service;

  Resource(String endpoint, Service service) {
    this.endpoint = endpoint;
    this.service = service;
  }
}
