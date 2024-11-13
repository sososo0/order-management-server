package com.sparta.ordermanagement.framework.persistence.entity.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {

    CREDIT_CARD("신용 카드"),
    PG_PROVIDER("결제 대행사");

    private final String value;
}
