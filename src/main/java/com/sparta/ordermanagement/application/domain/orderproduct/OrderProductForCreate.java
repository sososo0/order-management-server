package com.sparta.ordermanagement.application.domain.orderproduct;

public record OrderProductForCreate(String productId,
                                    int count,
                                    int orderPrice) {
}
