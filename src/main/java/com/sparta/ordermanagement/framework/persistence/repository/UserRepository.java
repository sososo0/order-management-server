package com.sparta.ordermanagement.framework.persistence.repository;

import com.sparta.ordermanagement.framework.persistence.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserStringId(String userStringId);
    Boolean existsByUserStringId(String userStringId);
}
