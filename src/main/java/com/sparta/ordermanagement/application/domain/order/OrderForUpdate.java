package com.sparta.ordermanagement.application.domain.order;

import com.sparta.ordermanagement.framework.persistence.entity.order.OrderState;

public record OrderForUpdate(String orderId,
                             String userId,
                             OrderState orderState,
                             String deliveryAddress,
                             String requestOrder,
                             String updateUserId) {
}
