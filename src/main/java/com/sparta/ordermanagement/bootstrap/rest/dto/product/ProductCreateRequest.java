package com.sparta.ordermanagement.bootstrap.rest.dto.product;

import com.sparta.ordermanagement.application.domain.product.ProductForCreate;
import com.sparta.ordermanagement.framework.persistence.entity.product.ProductState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    @NotBlank
    String productName;

    @NotBlank
    @Positive
    Integer productPrice;

    @NotBlank
    String productDescription;

    ProductState productState;

    public ProductForCreate toDomain(String shopUuid, String userUuid) {
        return new ProductForCreate(
            productName,
            productPrice,
            productDescription,
            productState,
            shopUuid,
            userUuid
        );
    }
}
