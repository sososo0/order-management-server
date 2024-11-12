package com.sparta.ordermanagement.bootstrap.rest.dto.product;

import com.sparta.ordermanagement.application.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDeleteResponse {

    private final String productUuid;
    private final String shopUuid;

    public static ProductDeleteResponse from (Product product) {
        return new ProductDeleteResponse(
            product.getProductUuid(),
            product.getShop().getUuid()
        );
    }
}
