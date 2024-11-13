package com.sparta.ordermanagement.framework.persistence.adapter;

import com.sparta.ordermanagement.application.domain.user.User;
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
    public Optional<User> findByUserStringId(String userStringId) {
        return userRepository.findByUserStringId(userStringId)
                .map(UserEntity::toDomain)
                .or(Optional::empty);
    }

    @Override
    public Boolean existsByUserStringId(String userStringId) {
        return userRepository.existsByUserStringId(userStringId);
    }

    @Override
    public String saveUser(User user, String encodedPassword) {
        UserEntity userEntity = UserEntity.from(user, encodedPassword);

        return userRepository.save(userEntity).getUserStringId();
    }
}
