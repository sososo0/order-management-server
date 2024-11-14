package com.sparta.ordermanagement.application.exception.order;

import com.sparta.ordermanagement.application.exception.InvalidValueException;

import static com.sparta.ordermanagement.application.exception.message.OrderErrorMessage.ORDER_CANCELLATION_TIME_EXCEEDED;

public class OrderCancellationTimeExceededException extends InvalidValueException {

    public OrderCancellationTimeExceededException(String orderId) {
        super(String.format(ORDER_CANCELLATION_TIME_EXCEEDED.getMessage(), orderId));
    }
}
