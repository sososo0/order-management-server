package com.sparta.ordermanagement.framework.persistence.adapter;

import com.sparta.ordermanagement.application.output.UserOutputPort;
import com.sparta.ordermanagement.framework.persistence.entity.user.UserEntity;
import com.sparta.ordermanagement.framework.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements UserOutputPort {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .or(Optional::empty);
    }
}
