package com.sparta.ordermanagement.application.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderErrorMessage {

    USER_ID_INVALID("유효하지 않은 유저 식별자 입니다. : %s"),
    ORDER_PRODUCT_ID_INVALID("유효하지 않은 주문 상품 식별자 입니다. : %s"),
    ORDER_ID_INVALID("유효하지 않은 주문 식별자 입니다. : %s"),
    ORDER_CANCELLATION_TIME_EXCEEDED("주문 후 5분이 지나 취소할 수 없습니다.");

    private final String message;
}
