package com.sparta.ordermanagement.bootstrap.rest.dto.user;

import com.sparta.ordermanagement.application.domain.user.User;
import com.sparta.ordermanagement.application.domain.user.UserForSignup;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;


@Getter
public class UserSignupRequest {

    @Pattern(
            regexp = "^[a-z0-9]{4,10}$",
            message = "아이디는 4자 이상 10자 이하의 알파벳 소문자와 숫자로 구성되어야 합니다."
    )
    private String userStringId;

    @Pattern(
            regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+~`|}{\\[\\]:;?><,./-]).{8,15}$",
            message = "비밀번호는 8자 이상 15자 이하의 알파벳, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;

    @NotNull
    private Role role;

    public User toDomain(){
        return new User(null, this.userStringId, this.password, this.role, null);
    }

}
