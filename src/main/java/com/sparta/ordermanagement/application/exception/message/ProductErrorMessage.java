package com.sparta.ordermanagement.application.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductErrorMessage {

    PRODUCT_UUID_INVALID("유효하지 않은 상품 식별자 입니다: %s"),
    PRODUCT_NOT_BELONG_TO_SHOP("상품 %s 는 가게 %s 에 등록되지 않은 상품입니다."),
    PRODUCT_DELETED("삭제된 상품 입니다.: %s");

    private final String message;
}
