package com.sparta.ordermanagement.bootstrap.rest.dto.user;

public record userSigninResponse(
        String grantType,
        String accessToken
) {

}
