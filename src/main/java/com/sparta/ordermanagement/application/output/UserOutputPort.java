package com.sparta.ordermanagement.application.output;


import com.sparta.ordermanagement.application.domain.user.User;
import com.sparta.ordermanagement.framework.persistence.entity.user.UserEntity;

import java.util.Optional;

public interface UserOutputPort {

    Optional<User> findByUserStringId(String userStringId);
    Boolean existsByUserStringId(String userStringId);
    String saveUser(User user, String encodedPassword);

}
