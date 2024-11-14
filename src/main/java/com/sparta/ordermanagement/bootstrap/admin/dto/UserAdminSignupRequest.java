package com.sparta.ordermanagement.bootstrap.admin.dto;

import com.sparta.ordermanagement.application.domain.user.User;
import com.sparta.ordermanagement.application.domain.user.UserForSignin;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import jakarta.validation.constraints.NotNull;

public record UserAdminSignupRequest(
        @NotNull
        String userStringId,
        @NotNull
        String password,
        @NotNull
        String role
) {
    public User toDomain(){

        return new User(null, this.userStringId, this.password, Role.valueOf(role), null);
    }
}
