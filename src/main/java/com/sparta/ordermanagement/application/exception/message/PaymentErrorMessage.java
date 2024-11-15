package com.sparta.ordermanagement.application.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentErrorMessage {

    USER_ORDER_ID_INVALID("주문에 대해 유효하지 않은 유저 아이디 입니다. : %s");

    private final String message;
}
