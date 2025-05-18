package com.codeaddi.row_your_boat.model.http.inbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceUseDTO<T> {
    private T resource;
    private List<ResourceInUse> inUseOnDate;
}
