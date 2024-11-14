package com.sparta.ordermanagement.application.exception.order;

import com.sparta.ordermanagement.application.exception.InvalidValueException;

import static com.sparta.ordermanagement.application.exception.message.OrderErrorMessage.ORDER_STATE_INVALID;

public class OrderStateInvalidException extends InvalidValueException {

    public OrderStateInvalidException() {
        super(String.format(ORDER_STATE_INVALID.getMessage()));
    }
}
