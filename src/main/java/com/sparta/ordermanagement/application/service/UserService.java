package com.sparta.ordermanagement.application.service;


import com.sparta.ordermanagement.application.domain.shop.Shop;
import com.sparta.ordermanagement.application.domain.user.User;
import com.sparta.ordermanagement.application.domain.user.UserForSignup;
import com.sparta.ordermanagement.application.exception.InvalidValueException;
import com.sparta.ordermanagement.application.exception.shop.ShopIdInvalidException;
import com.sparta.ordermanagement.application.output.UserOutputPort;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserOutputPort userOutputPort;

    @Transactional
    public String signup(User user){

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        //회원 중복 확인
        if(userOutputPort.existsByUserStringId(user.getUserStringId())){
            throw new InvalidValueException("동일한 Id를 가진 유저가 이미 존재합니다.");
        }

        //회원 권한 확인
        if(user.getRole().equals(Role.MANAGER)|| user.getRole().equals(Role.MASTER)){
            throw new InvalidValueException("관리자 계정은 등록할 수 없습니다.");
        }

        return userOutputPort.saveUser(user, encodedPassword);
    }


}
