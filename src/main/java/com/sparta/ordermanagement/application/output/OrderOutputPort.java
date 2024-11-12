package com.sparta.ordermanagement.application.output;

import com.sparta.ordermanagement.application.domain.order.OrderForCreate;
import com.sparta.ordermanagement.framework.persistence.entity.user.UserEntity;

public interface OrderOutputPort {

    String saveOrder(OrderForCreate orderForCreate, UserEntity userEntity);
}
