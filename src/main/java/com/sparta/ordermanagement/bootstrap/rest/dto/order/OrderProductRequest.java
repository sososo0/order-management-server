package com.sparta.ordermanagement.bootstrap.rest.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductRequest {

    @NotBlank
    String productId;

    @Min(1)
    int count;

    @Min(1000)
    int orderPrice;
}
