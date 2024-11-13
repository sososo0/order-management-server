package com.sparta.ordermanagement.bootstrap.rest.dto.product;

import com.sparta.ordermanagement.application.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductListResponse {

    private final String productUuid;
    private final String productName;
    private final Integer productPrice;
    private final String productDescription;

    public static ProductListResponse from(Product product) {
        return new ProductListResponse(
            product.getProductUuid(),
            product.getProductName(),
            product.getProductPrice(),
            product.getProductDescription()
        );
    }
}
