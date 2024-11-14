package com.sparta.ordermanagement.framework.persistence.entity.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {

    ONLINE("온라인"),
    OFFLINE("오프라인");

    private final String value;
}
