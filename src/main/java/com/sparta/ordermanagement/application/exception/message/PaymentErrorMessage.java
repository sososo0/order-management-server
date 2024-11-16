package com.sparta.ordermanagement.application.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentErrorMessage {

    USER_ORDER_ID_INVALID("주문에 대해 유효하지 않은 유저 아이디 입니다. : %s"),
    PAYMENT_PROCESS_FAILED("결제가 실패했습니다. 다시 시도해 주세요."),
    PAYMENT_PROCESS_ERROR("결제 진행 중 문제가 발생했습니다.");

    private final String message;
}
