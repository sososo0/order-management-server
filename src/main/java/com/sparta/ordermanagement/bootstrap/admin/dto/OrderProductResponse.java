package com.sparta.ordermanagement.bootstrap.admin.dto;

import com.sparta.ordermanagement.framework.persistence.entity.orderproduct.OrderProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse {

    private Long orderProductId;
    private String productUuid;
    private String productName;
    private Integer count;
    private Integer orderPrice;

    public static OrderProductResponse from(OrderProductEntity orderProductEntity) {
        return new OrderProductResponse(
                orderProductEntity.getId(),
                orderProductEntity.getProductEntity().getProductUuid(),
                orderProductEntity.getProductEntity().getProductName(),
                orderProductEntity.getCount(),
                orderProductEntity.getOrderPrice()
        );
    }
}
