package com.sparta.ordermanagement.application.domain.order;

import com.sparta.ordermanagement.framework.persistence.entity.order.OrderType;

import java.time.LocalDateTime;

public class Order {

    private String orderUuid;
    private String userId;
    private String shopId;
    private String shopName;
    private OrderState orderState;
    private OrderType orderType;
    private String deliveryAddress;
    private String requestOrder;
    private LocalDateTime createdAt;
    private boolean isDeleted;

    public Order(String orderUuid, OrderState orderState,
                 OrderType orderType, String deliveryAddress, String requestOrder,
                 String shopId, String shopName, String userId, LocalDateTime createdAt, boolean isDeleted) {

        this.orderUuid = orderUuid;
        this.orderState = orderState;
        this.orderType = orderType;
        this.deliveryAddress = deliveryAddress;
        this.requestOrder = requestOrder;
        this.shopId = shopId;
        this.shopName = shopName;
        this.userId = userId;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
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

    public String getShopName() {
        return shopName;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public boolean isSameReviewer(String userStringId) {
        return userId.equals(userStringId);
    }
}
