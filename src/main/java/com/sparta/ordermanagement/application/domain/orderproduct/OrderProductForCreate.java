package com.sparta.ordermanagement.application.domain.orderproduct;

public record OrderProductForCreate(String productId,
                                    int productQuantity,
                                    String userId) {
}
