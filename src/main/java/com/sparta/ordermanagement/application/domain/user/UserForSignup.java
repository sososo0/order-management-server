package com.sparta.ordermanagement.application.domain.user;

public record UserForSignup(
        String userStringId,
        String password,
        String role
) {

}
