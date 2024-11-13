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

    /* Order */
    @NotBlank
    String userId;

    @NotBlank
    String shopId;

    @NotBlank
    OrderState orderState;

    @NotBlank
    OrderType orderType;

    String deliveryAddress;

    String requestOrder;

    /* OrderProduct */
    @NotBlank
    String productId;

    @NotBlank
    int count;

    @NotBlank
    int orderPrice;

    public OrderForCreate toDomain(String createdUserId) {
        return new OrderForCreate(userId, shopId, orderState, orderType, deliveryAddress,
                requestOrder, productId, count, orderPrice, createdUserId);
    }
}
