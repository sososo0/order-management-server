package com.sparta.ordermanagement.application.output;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.order.OrderForCreate;

public interface OrderOutputPort {

    Order saveOrder(OrderForCreate orderForCreate);
}
