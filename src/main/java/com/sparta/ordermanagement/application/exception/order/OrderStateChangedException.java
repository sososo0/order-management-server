package com.sparta.ordermanagement.application.exception.order;

import com.sparta.ordermanagement.application.exception.InvalidValueException;

import static com.sparta.ordermanagement.application.exception.message.OrderErrorMessage.ORDER_STATE_CANNOT_DB_CHANGED;

public class OrderStateChangedException extends InvalidValueException {
    /* 더 좋은 이름이 있으면 변경하고 싶음*/
    public OrderStateChangedException(String orderId) {
        super(String.format(ORDER_STATE_CANNOT_DB_CHANGED.getMessage(), orderId));
    }
}
