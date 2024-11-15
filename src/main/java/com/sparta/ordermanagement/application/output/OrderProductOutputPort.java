package com.sparta.ordermanagement.application.output;

import com.sparta.ordermanagement.application.domain.orderproduct.OrderProduct;

import java.util.List;

public interface OrderProductOutputPort {
    List<OrderProduct> findOrderProductsByOrderId(String orderId);
}
