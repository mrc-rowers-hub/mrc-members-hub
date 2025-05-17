package com.codeaddi.row_your_boat.model.http.inbound;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ResourceUseDTO<T> {
    private T resource;
    private List<ResourceInUse> inUseOnDate;
}
