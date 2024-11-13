package com.sparta.ordermanagement.framework.persistence.adapter;

import com.sparta.ordermanagement.application.output.OrderProductOutputPort;
import com.sparta.ordermanagement.framework.persistence.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderProductPersistenceAdapter implements OrderProductOutputPort {

    private final OrderProductRepository orderProductRepository;
}
