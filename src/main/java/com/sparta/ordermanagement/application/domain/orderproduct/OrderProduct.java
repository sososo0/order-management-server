package com.sparta.ordermanagement.application.domain.orderproduct;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.product.Product;

public class OrderProduct {

    private Long id;
    private Order order;
    private Product product;
    private int count;
    private int orderPrice;

    public OrderProduct(Long id, Order order, Product product, int count, int orderPrice) {

        this.id = id;
        this.order = order;
        this.product = product;
        this.count = count;
        this.orderPrice = orderPrice;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

}
