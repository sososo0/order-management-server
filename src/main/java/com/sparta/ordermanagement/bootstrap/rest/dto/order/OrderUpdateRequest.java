package com.sparta.ordermanagement.bootstrap.rest.dto.order;

import com.sparta.ordermanagement.application.domain.order.OrderForUpdate;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderState;
import jakarta.validation.constraints.NotBlank;

public class OrderUpdateRequest {

    @NotBlank
    String orderId;

    @NotBlank
    String userId;

    @NotBlank
    OrderState orderState;

    String deliveryAddress;

    String requestOrder;

    public OrderForUpdate toDomain(String orderId, String updateUserId) {
        return new OrderForUpdate(orderId, userId, orderState,
                deliveryAddress, requestOrder, updateUserId);
    }
}
