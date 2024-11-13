package com.sparta.ordermanagement.application.service;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.orderproduct.OrderProduct;
import com.sparta.ordermanagement.application.domain.orderproduct.OrderProductForCreate;
import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.application.output.OrderProductOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderProductService {

    private final OrderProductOutputPort orderProductOutputPort;


    public List<OrderProduct> createOrderProduct(OrderProductForCreate orderProductForCreate, Order order) {
        return null;
    }

    private Product validateProductIdAndGet(String productId) {
        return null;
    }
}
