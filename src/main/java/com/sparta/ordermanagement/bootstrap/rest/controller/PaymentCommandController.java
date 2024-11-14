package com.sparta.ordermanagement.bootstrap.rest.controller;

import com.sparta.ordermanagement.application.service.PaymentService;
import com.sparta.ordermanagement.bootstrap.auth.UserDetailsImpl;
import com.sparta.ordermanagement.bootstrap.rest.dto.payment.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders/payments")
public class PaymentCommandController {

    private final PaymentService paymentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String payment(@RequestBody PaymentRequest paymentRequest,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return null;
    }
}
