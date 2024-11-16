package com.sparta.ordermanagement.bootstrap.rest.dto.order;

import com.sparta.ordermanagement.application.domain.orderproduct.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse {

    private String productUuid;
    private String productName;
    private Integer count;
    private Integer orderPrice;

    public static OrderProductResponse from(OrderProduct orderProduct) {

        return new OrderProductResponse(
                orderProduct.getProduct().getProductUuid(),
                orderProduct.getProduct().getProductName(),
                orderProduct.getCount(),
                orderProduct.getOrderPrice()
        );
    }
}
