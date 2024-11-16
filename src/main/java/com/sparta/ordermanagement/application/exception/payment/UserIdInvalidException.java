package com.sparta.ordermanagement.application.exception.payment;

import com.sparta.ordermanagement.application.exception.InvalidValueException;

import static com.sparta.ordermanagement.application.exception.message.OrderErrorMessage.USER_ID_INVALID;

public class UserIdInvalidException extends InvalidValueException {


    public UserIdInvalidException(String userId) {
        super(String.format(USER_ID_INVALID.getMessage(), userId));
    }
}
