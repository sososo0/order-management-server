package com.sparta.ordermanagement.bootstrap.rest.controller.product;

import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.application.domain.product.ProductForCreate;
import com.sparta.ordermanagement.application.domain.product.ProductForDelete;
import com.sparta.ordermanagement.application.domain.product.ProductStateForUpdate;
import com.sparta.ordermanagement.application.domain.product.ProductForUpdate;
import com.sparta.ordermanagement.application.service.product.ProductService;
import com.sparta.ordermanagement.bootstrap.auth.UserDetailsImpl;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductUpdateRequest;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductCreateRequest;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductCreateResponse;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductDeleteResponse;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductStateUpdateRequest;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductStateUpdateResponse;
import com.sparta.ordermanagement.bootstrap.rest.dto.product.ProductUpdateResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 - 상품 관리", description = "OWNER 이상의 권한 필요")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/shops/{shopUuid}/products")
public class ProductCommandController {

    private final ProductService productService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductCreateResponse createProduct(
        @PathVariable(value = "shopUuid") String shopUuid,
        @Valid @RequestBody ProductCreateRequest productCreateRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        ProductForCreate productForCreate = productCreateRequest.toDomain(
            shopUuid,
            userDetails.getUserStringId(),
            userDetails.getRole()
        );
        Product product = productService.createProduct(productForCreate);

        return ProductCreateResponse.from(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{productUuid}")
    public ProductUpdateResponse updateProduct(
        @PathVariable(value = "shopUuid") String shopUuid,
        @PathVariable(value = "productUuid") String productUuid,
        @Valid @RequestBody ProductUpdateRequest productUpdateRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        ProductForUpdate productForUpdate = productUpdateRequest.toDomain(
            shopUuid,
            productUuid,
            userDetails.getUserStringId(),
            userDetails.getRole()
        );
        Product product = productService.updateProduct(productForUpdate);

        return ProductUpdateResponse.from(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{productUuid}")
    public ProductStateUpdateResponse updateProductState(
        @PathVariable(value = "shopUuid") String shopUuid,
        @PathVariable(value = "productUuid") String productUuid,
        @Valid @RequestBody ProductStateUpdateRequest productStateUpdateRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        ProductStateForUpdate productStateForUpdate = productStateUpdateRequest.toDomain(
            shopUuid,
            productUuid,
            userDetails.getUserStringId(),
            userDetails.getRole()
        );
        Product product = productService.updateProductState(productStateForUpdate);

        return ProductStateUpdateResponse.from(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{productUuid}")
    public ProductDeleteResponse deleteProduct(
        @PathVariable(value = "shopUuid") String shopUuid,
        @PathVariable(value = "productUuid") String productUuid,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        ProductForDelete productForDelete = new ProductForDelete(
            true,
            shopUuid,
            productUuid,
            userDetails.getUserStringId(),
            userDetails.getRole()
        );
        Product product = productService.deleteProduct(productForDelete);

        return ProductDeleteResponse.from(product);
    }
}
