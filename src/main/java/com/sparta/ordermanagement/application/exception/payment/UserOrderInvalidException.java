package com.sparta.ordermanagement.application.exception.payment;

import com.sparta.ordermanagement.application.exception.InvalidValueException;

import static com.sparta.ordermanagement.application.exception.message.PaymentErrorMessage.USER_ORDER_ID_INVALID;

public class UserOrderInvalidException extends InvalidValueException {

    public UserOrderInvalidException(String userId) {
        super(String.format(USER_ORDER_ID_INVALID.getMessage(), userId));
    }
}
