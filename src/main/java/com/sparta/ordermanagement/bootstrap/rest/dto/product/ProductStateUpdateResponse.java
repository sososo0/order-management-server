package com.sparta.ordermanagement.bootstrap.rest.dto.product;

import com.sparta.ordermanagement.application.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductStateUpdateResponse {

    private final String productUuid;
    private final String shopUuid;

    public static ProductStateUpdateResponse from (Product product) {
        return new ProductStateUpdateResponse(
            product.getProductUuid(),
            product.getShop().getUuid()
        );
    }
}
