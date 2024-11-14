package com.sparta.ordermanagement.application.domain.order;

import com.sparta.ordermanagement.framework.persistence.entity.order.OrderState;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderType;

public class Order {

    private Long id;
    private String orderUuid;
    private String userId;
    private String shopId;
    private OrderState orderState;
    private OrderType orderType;
    private String deliveryAddress;
    private String requestOrder;

    public Order(Long id, String orderUuid, OrderState orderState,
                 OrderType orderType, String deliveryAddress, String requestOrder,
                 String shopId, String userId) {

        this.id = id;
        this.orderUuid = orderUuid;
        this.orderState = orderState;
        this.orderType = orderType;
        this.deliveryAddress = deliveryAddress;
        this.requestOrder = requestOrder;
        this.shopId = shopId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getRequestOrder() {
        return requestOrder;
    }

    public String getShopId() { return shopId; }

    public String getUserId() {
        return userId;
    }

}
