package com.sparta.ordermanagement.application.domain.order;

public record OrderForUpdate(String orderId,
                             String userId,
                             OrderState orderState,
                             String updateUserId) {
}
