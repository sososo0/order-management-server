package com.sparta.ordermanagement.application.domain.order;

public record OrderForUpdate(OrderState orderState,
                             String userId,
                             String orderId,
                             String updateUserId) {
}
