package com.sparta.ordermanagement.bootstrap.rest.controller;

import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.application.domain.product.ProductForCreate;
import com.sparta.ordermanagement.application.domain.product.ProductForDelete;
import com.sparta.ordermanagement.application.domain.product.ProductStateForUpdate;
import com.sparta.ordermanagement.application.service.ProductService;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductCreateRequest;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductCreateResponse;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductDeleteResponse;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductStateUpdateRequest;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductStateUpdateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    // TODO: 추후에 지울 예정
    private static final String TEST_CREATED_USER_ID = "0000";

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductCreateResponse createProduct(
        @PathVariable(value = "shopUuid") String shopUuid,
        @Valid @RequestBody ProductCreateRequest productCreateRequest
    ) {

        // TODO : OWNER 인지 확인

        ProductForCreate productForCreate = productCreateRequest.toDomain(
            shopUuid,
            TEST_CREATED_USER_ID
        );
        Product product = productService.createProduct(productForCreate);

        return ProductCreateResponse.from(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{productUuid}")
    public ProductStateUpdateResponse updateProductState(
        @PathVariable(value = "shopUuid") String shopUuid,
        @PathVariable(value = "productUuid") String productUuid,
        @Valid @RequestBody ProductStateUpdateRequest productStateUpdateRequest
    ) {

        // TODO : OWNER 인지 확인

        ProductStateForUpdate productStateForUpdate = productStateUpdateRequest.toDomain(
            shopUuid,
            productUuid,
            TEST_CREATED_USER_ID
        );
        Product product = productService.updateProductState(productStateForUpdate);

        return ProductStateUpdateResponse.from(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{productUuid}")
    public ProductDeleteResponse deleteProduct(
        @PathVariable(value = "shopUuid") String shopUuid,
        @PathVariable(value = "productUuid") String productUuid
    ) {

        // TODO : OWNER 인지 확인

        ProductForDelete productForDelete = new ProductForDelete(
            true,
            shopUuid,
            productUuid,
            TEST_CREATED_USER_ID
        );
        Product product = productService.deleteProduct(productForDelete);

        return ProductDeleteResponse.from(product);
    }

}
