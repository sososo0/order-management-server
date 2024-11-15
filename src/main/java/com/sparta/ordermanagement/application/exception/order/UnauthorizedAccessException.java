package com.sparta.ordermanagement.application.exception.order;

import com.sparta.ordermanagement.application.exception.InvalidValueException;

import static com.sparta.ordermanagement.application.exception.message.OrderErrorMessage.UNAUTHORIZED_ACCESS;

public class UnauthorizedAccessException extends InvalidValueException {

    public UnauthorizedAccessException() {
        super(String.format(UNAUTHORIZED_ACCESS.getMessage()));
    }
}
