package com.sparta.ordermanagement.application.domain.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentState {

    PENDING,
    COMPLETED,
    CANCELED,
    FAILED;
}
