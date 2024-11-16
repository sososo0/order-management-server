package com.sparta.ordermanagement.application.domain.payment;

public record PaymentForUpdate(String orderUuid,
                               String paymentUuid,
                               String pgProvider,
                               String updatedUserId) {
}
