package com.sparta.ordermanagement.application.exception.order;

import com.sparta.ordermanagement.application.exception.InvalidValueException;
import static com.sparta.ordermanagement.application.exception.message.OrderErrorMessage.USER_ID_INVALID;

public class OrderUserIdInvalidException extends InvalidValueException {

    public OrderUserIdInvalidException(String userId) {
        super(String.format(USER_ID_INVALID.getMessage(), userId));
    }
}
