package com.sparta.ordermanagement.application.domain.order;

import com.sparta.ordermanagement.application.domain.orderproduct.OrderProduct;
import com.sparta.ordermanagement.application.domain.payment.PaymentState;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderType;

import java.util.List;

public class TotalOrder {

    private String orderUuid;
    private String userId;
    private String shopId;
    private String shopName;
    private OrderState orderState;
    private OrderType orderType;
    private String deliveryAddress;
    private String requestOrder;
    private List<OrderProduct> orderProducts;
    private String paymentUuid;
    private PaymentState paymentState;
    private Integer amount;
    private String pgProvider;
    private boolean isDeleted;

    public TotalOrder(String orderUuid, String userId, String shopId, String shopName,
                      OrderState orderState, OrderType orderType, String deliveryAddress,
                      String requestOrder, List<OrderProduct> orderProducts, String paymentUuid,
                      PaymentState paymentState, Integer amount, String pgProvider, boolean isDeleted) {

        this.orderUuid = orderUuid;
        this.userId = userId;
        this.shopId = shopId;
        this.shopName = shopName;
        this.orderState = orderState;
        this.orderType = orderType;
        this.deliveryAddress = deliveryAddress;
        this.requestOrder = requestOrder;
        this.orderProducts = orderProducts;
        this.paymentUuid = paymentUuid;
        this.paymentState = paymentState;
        this.amount = amount;
        this.pgProvider = pgProvider;
        this.isDeleted = isDeleted;
    }

    public String getOrderUuid() {
        return orderUuid;
    }

    public String getUserId() {
        return userId;
    }

    public String getShopId() {
        return shopId;
    }

    public String getShopName() {
        return shopName;
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

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public String getPaymentUuid() {
        return paymentUuid;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getPgProvider() {
        return pgProvider;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }
}
