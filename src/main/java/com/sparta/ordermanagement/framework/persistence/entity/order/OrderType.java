package com.sparta.ordermanagement.framework.persistence.entity.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderType {

    ONLINE,
    OFFLINE;

}
