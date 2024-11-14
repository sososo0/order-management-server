package com.sparta.ordermanagement.application.domain.orderproduct;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;

public class OrderProduct {

    private Long id;
    private String OrderProductUuid;
    private Order order;
    private Product product;
    private int productQuantity;

    public OrderProduct(Long id, String OrderProductUuid, Order order,
                        Product product, int productQuantity) {

        this.id = id;
        this.OrderProductUuid = OrderProductUuid;
        this.order = order;
        this.product = product;
        this.productQuantity = productQuantity;
    }

    public Long getId() {
        return id;
    }

    public String getOrderProductUuid() {
        return OrderProductUuid;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.order = orderEntity.toDomain();
    }
}
