package com.sparta.ordermanagement.application.exception.order;

import com.sparta.ordermanagement.application.exception.InvalidValueException;
import static com.sparta.ordermanagement.application.exception.message.OrderErrorMessage.ORDER_ID_INVALID;

public class InvalidOrderException extends InvalidValueException {

    public InvalidOrderException(String orderId) {
        super(String.format(ORDER_ID_INVALID.getMessage(), orderId));
    }
}
