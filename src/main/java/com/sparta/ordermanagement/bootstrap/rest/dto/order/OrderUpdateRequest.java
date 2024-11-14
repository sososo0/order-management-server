package com.sparta.ordermanagement.bootstrap.rest.dto.order;

import com.sparta.ordermanagement.application.domain.order.OrderForUpdate;
import com.sparta.ordermanagement.application.domain.order.OrderState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class OrderUpdateRequest {

    @NotNull
    String orderState;

    @NotNull
    String userId;

    public OrderForUpdate toDomain(String orderId, String updateUserId) {
        OrderState updatedState = OrderState.valueOfOrThrow(this.orderState);
        return new OrderForUpdate(updatedState, userId, orderId, updateUserId);
    }
}
