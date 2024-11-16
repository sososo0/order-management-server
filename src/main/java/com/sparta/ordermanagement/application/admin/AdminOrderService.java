package com.sparta.ordermanagement.application.admin;

import com.sparta.ordermanagement.application.domain.order.OrderState;
import com.sparta.ordermanagement.application.exception.InvalidValueException;
import com.sparta.ordermanagement.application.exception.order.InvalidOrderException;
import com.sparta.ordermanagement.bootstrap.admin.dto.OrderResponse;
import com.sparta.ordermanagement.bootstrap.admin.dto.OrderUpdateRequest;
import com.sparta.ordermanagement.bootstrap.admin.dto.OrderUpdateResponse;
import com.sparta.ordermanagement.framework.admin.repository.AdminOrderRepository;
import com.sparta.ordermanagement.framework.admin.repository.AdminPaymentRepository;
import com.sparta.ordermanagement.framework.persistence.entity.order.OrderEntity;
import com.sparta.ordermanagement.framework.persistence.entity.payment.PaymentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final AdminOrderRepository adminOrderRepository;
    private final AdminPaymentRepository adminPaymentRepository;

    @Transactional
    public OrderUpdateResponse updateOrder(String orderId, OrderUpdateRequest orderUpdateRequest, String userStringId) {
        OrderEntity order = validateOrderIdAndGetOrder(orderId);

        order.updateFrom(orderUpdateRequest, userStringId);
        String paymentUuid = checkCanceled(order);

        return OrderUpdateResponse.from(order, paymentUuid);
    }

    @Transactional
    public String deleteOrder(String orderId, String userId) {
        OrderEntity order = validateOrderIdAndGetOrder(orderId);

        if (order.getOrderState().equals(OrderState.CANCELED)) {
            order.deleteFrom(userId);
            return orderId;
        }

        throw new InvalidValueException("주문 취소 상태가 아니어서 주문을 삭제할 수 없습니다.");
    }

    public Page<OrderEntity> findAll(Pageable pageable) {
        return PageableSortProxy.executeWithFallback(pageable, updatePageable ->
                adminOrderRepository.findAll(pageable));
    }

    public OrderResponse findOrder(String orderId) {
        return OrderResponse.from(adminOrderRepository.findByOrderUuid(orderId)
                .orElseThrow(() -> new InvalidOrderException(orderId)));
    }

    private OrderEntity validateOrderIdAndGetOrder(String orderId) {
        return adminOrderRepository.findByOrderUuid(orderId)
                .orElseThrow(() -> new InvalidOrderException(orderId));
    }

    private String checkCanceled(OrderEntity order) {
        if (order.getOrderState().equals(OrderState.CANCELED)) {
            PaymentEntity payment = adminPaymentRepository.findByOrderEntity(order)
                    .orElseThrow(() -> new InvalidValueException("주문에 해당하는 결제 정보가 없습니다."));

            return payment.getPaymentUuid();
        }
        return null;
    }
}
