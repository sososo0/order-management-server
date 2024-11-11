package com.sparta.ordermanagement.bootstrap.rest.controller;

import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.application.domain.product.ProductForCreate;
import com.sparta.ordermanagement.application.service.ProductService;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductCreateRequest;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/shops/{shopUuid}/products")
public class ProductCommandController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductCreateResponse createProduct(
        @PathVariable(value = "shopUuid") String shopUuid,
        @RequestBody ProductCreateRequest productCreateRequest
    ) {

        ProductForCreate productForCreate = productCreateRequest.toDomain(shopUuid);
        Product product = productService.createProduct(productForCreate);

        return ProductCreateResponse.from(product);
    }
}
