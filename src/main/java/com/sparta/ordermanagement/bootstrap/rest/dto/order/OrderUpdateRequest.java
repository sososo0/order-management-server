package com.sparta.ordermanagement.bootstrap.rest.dto.order;

import com.sparta.ordermanagement.application.domain.order.OrderForUpdate;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderState;
import jakarta.validation.constraints.NotBlank;

public class OrderUpdateRequest {

    @NotBlank
    String userId;

    @NotBlank
    OrderState orderState;

    public OrderForUpdate toDomain(String orderId, String updateUserId) {
        return new OrderForUpdate(orderId, userId, orderState, updateUserId);
    }
}
