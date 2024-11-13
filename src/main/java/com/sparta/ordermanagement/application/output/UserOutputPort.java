package com.sparta.ordermanagement.application.output;

import com.sparta.ordermanagement.framework.persistence.entity.user.UserEntity;

import java.util.Optional;

public interface UserOutputPort {
    Optional<UserEntity> findByUserId(String userId);
}
