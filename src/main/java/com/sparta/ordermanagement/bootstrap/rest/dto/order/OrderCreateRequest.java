package com.sparta.ordermanagement.bootstrap.rest.dto.order;

import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderState;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {

    @NotBlank
    String orderId;

    @NotBlank
    String userId;

    @NotBlank
    OrderState orderState;

    @NotBlank
    OrderType orderType;

    String deliverAddress;

    String requestOrder;

    public OrderForCreate toDomain(String createUserId) {
        return new OrderForCreate(orderId, userId, orderState, orderType,
                deliverAddress, requestOrder, createUserId);
    }
}
