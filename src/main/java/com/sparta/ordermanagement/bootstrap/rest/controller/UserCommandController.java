package com.sparta.ordermanagement.bootstrap.rest.controller;

import com.sparta.ordermanagement.application.exception.InvalidValueException;
import com.sparta.ordermanagement.application.service.UserService;
import com.sparta.ordermanagement.bootstrap.rest.dto.user.UserSigninRequest;
import com.sparta.ordermanagement.bootstrap.rest.dto.user.UserSignupRequest;
import com.sparta.ordermanagement.bootstrap.rest.exception.exceptions.RequestValidationException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserCommandController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public Map.Entry signupUser(@RequestBody @Valid UserSignupRequest userSignupRequest, BindingResult bindingResult) {

        log.info("[UserCommandController]-[signupUser] API call");

        // Validation
        if (bindingResult.hasErrors()) {
            log.error("[UserCommandController]-[signupUser] error");
            String bindingErrorMessage = bindingResult.getAllErrors().toString();
            throw new RequestValidationException(bindingErrorMessage);
        }

        String signupUserStringId = userService.signup(userSignupRequest.toDomain());

        return Map.entry("userStringId", signupUserStringId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signin")
    public ResponseEntity<String> signinUser(@RequestBody @Valid UserSigninRequest userSigninRequest, BindingResult bindingResult){

        log.info("[UserCommandController]-[signinUser] API call");

        // Validation
        if (bindingResult.hasErrors()) {
            log.error("[UserCommandController]-[signinUser] error");
            String bindingErrorMessage = bindingResult.getAllErrors().toString();
            throw new RequestValidationException(bindingErrorMessage);
        }

        String accessToken = userService.signin(userSigninRequest.toDomain());

        // 응답 헤더에 토큰 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body("user signin successfully");
    }

}
