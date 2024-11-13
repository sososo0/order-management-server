package com.sparta.ordermanagement.application.domain.payment;

import com.sparta.ordermanagement.framework.persistence.entity.payment.PaymentState;
import com.sparta.ordermanagement.framework.persistence.entity.payment.PaymentType;

public record PaymentForCreate(PaymentType paymentType,
                               PaymentState paymentState,
                               String pgProvider,
                               String userId) {
}
