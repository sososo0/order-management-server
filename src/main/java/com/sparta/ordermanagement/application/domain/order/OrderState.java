package com.sparta.ordermanagement.application.domain.order;

import com.sparta.ordermanagement.application.exception.order.OrderStateInvalidException;

public enum OrderState {

    PENDING,
    APPROVED,
    COMPLETED,
    CANCELED;

    public static OrderState valueOfOrThrow(String requestOrderState) {

        for (OrderState orderState : OrderState.values()) {
            if (orderState.name().equalsIgnoreCase(requestOrderState)) {
                return orderState;
            }
        }
        throw new OrderStateInvalidException();
    }
}
