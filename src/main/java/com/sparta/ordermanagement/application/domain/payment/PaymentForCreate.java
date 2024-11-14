package com.sparta.ordermanagement.application.domain.payment;

public record PaymentForCreate(String orderId,
                               String userId,
                               String createdUserId) {
}
