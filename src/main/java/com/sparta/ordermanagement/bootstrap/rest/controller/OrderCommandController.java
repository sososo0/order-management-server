package com.sparta.ordermanagement.bootstrap.rest.controller;

import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.application.domain.order.OrderForUpdate;
import com.sparta.ordermanagement.application.service.OrderService;
import com.sparta.ordermanagement.bootstrap.rest.dto.order.OrderCreateRequest;
import com.sparta.ordermanagement.bootstrap.rest.dto.order.OrderUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderCommandController {

    // 삭제 예정: 추후 토큰으로부터 사용자 식별자를 받아와 대체할 것
    private static final String TEST_CREATED_USER_ID = "0000";

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String createOrder(@RequestBody OrderCreateRequest orderCreateRequest) {

        OrderForCreate orderForCreate = orderCreateRequest.toDomain(TEST_CREATED_USER_ID);
        return orderService.createOrder(orderForCreate);
    }

    // 주문 상태 변경 시 권한 검증은 Spring Security 기능 활성화 후 @PreAuthorize 등을 사용해 추가 예정
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{order_id}")
    public String updateOrderState(@PathVariable("order_id") String orderId, @RequestBody OrderUpdateRequest orderUpdateRequest) {

        OrderForUpdate orderForUpdate = orderUpdateRequest.toDomain(orderId, TEST_CREATED_USER_ID);
        return orderService.updateOrderState(orderForUpdate);
    }

    // 주문 취소자의 정보는 JWT 통합 후 @AuthenticationPrincipal 등을 사용해 확인할 예정
    // Owner 이상의 권한을 가진 유저의 경우 주문 상태 변경 메서드에서도 Cancel로 바꿀 수 있도록 설정?
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("{order_id}/cancel")
    public String cancelOrder(@PathVariable("order_id") String orderId) {

        return orderService.cancelOrder(orderId);
    }
}
