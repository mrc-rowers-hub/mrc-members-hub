package com.codeaddi.row_your_boat.model.http;

import com.codeaddi.row_your_boat.model.http.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@AllArgsConstructor
@Jacksonized
public class StandardResponse {
  private Status status;
  private String message;
}
