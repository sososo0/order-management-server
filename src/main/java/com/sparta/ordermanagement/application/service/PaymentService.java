package com.sparta.ordermanagement.application.service;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.payment.PaymentForCreate;
import com.sparta.ordermanagement.application.output.PaymentOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentOutputPort paymentOutputPort;

    public String createPayment(String orderId) {

        return paymentOutputPort.savePayment(orderId);
    }

    /* 결제를 위해 update를 보내면 orderId를 통해 orderEntity의 orderProduct 정보를 가져와서 결제 금액을 산정
    * 그러려면 여기서도 orderOutPort의 역할이 필요하다 혹은 orderService에서 기능을 만들고 불러와서 쓴다.
    * 또 다른 방안 orderProductService를 만들고 orderId를 기준으로 해당하는 OrderProduct 정보를 가져온다.*/
}
