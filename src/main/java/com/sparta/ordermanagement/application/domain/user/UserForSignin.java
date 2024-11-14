package com.sparta.ordermanagement.application.domain.user;

public record UserForSignin(
        String userStringId,
        String password,
        String grantType,
        String accessToken
) {
}
