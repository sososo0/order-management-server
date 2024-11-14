package com.sparta.ordermanagement.application.admin;

import com.sparta.ordermanagement.application.domain.user.User;
import com.sparta.ordermanagement.application.exception.InvalidValueException;
import com.sparta.ordermanagement.application.output.UserOutputPort;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import com.sparta.ordermanagement.framework.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserOutputPort userOutputPort;

    @Transactional
    public String signup(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        //회원 중복 확인
        if(userOutputPort.existsByUserStringId(user.getUserStringId())){
            throw new InvalidValueException("동일한 Id를 가진 유저가 이미 존재합니다.");
        }

        //회원 권한 확인
        if(!(user.getRole().equals(Role.MANAGER)|| user.getRole().equals(Role.MASTER))){
            throw new InvalidValueException("일반 계정 등록은 다른 API 를 사용해야 합니다.");

        }

        return userOutputPort.saveUser(user, encodedPassword);

    }
}
