package com.sparta.ordermanagement.application.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderProductErrorMessage {

    PRODUCT_ID_INVALID("유효하지 않은 상품 식별자 입니다. : %s"),
    ORDER_ID_INVALID("유효하지 않은 주문 식별자 입니다. : %s"),
    ORDER_PRODUCT_ID_INVALID("유효하지 않은 주문 상품 식별자 입니다. : %s");

    private final String message;
}
