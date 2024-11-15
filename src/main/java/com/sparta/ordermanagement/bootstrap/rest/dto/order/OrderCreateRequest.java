package com.sparta.ordermanagement.bootstrap.rest.dto.order;

import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.application.domain.order.OrderState;
import com.sparta.ordermanagement.application.domain.orderproduct.OrderProductForCreate;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

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
    @NotEmpty
    List<OrderProductRequest> products;

    public OrderForCreate toDomain(String createdUserId) {
        List<OrderProductForCreate> productList = products.stream()
                .map(product -> new OrderProductForCreate(product.getProductId(), product.getCount(), product.getOrderPrice()))
                .collect(Collectors.toList());

        return new OrderForCreate(userId, shopId, orderState, orderType, deliveryAddress,
                requestOrder, productList, createdUserId);
    }
}

