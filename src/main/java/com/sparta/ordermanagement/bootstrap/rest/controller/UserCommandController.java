package com.sparta.ordermanagement.bootstrap.rest.controller;

import com.sparta.ordermanagement.application.exception.InvalidValueException;
import com.sparta.ordermanagement.application.service.UserService;
import com.sparta.ordermanagement.bootstrap.rest.dto.user.UserSigninRequest;
import com.sparta.ordermanagement.bootstrap.rest.dto.user.UserSignupRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserCommandController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public String signupUser(@RequestBody @Valid UserSignupRequest userSignupRequest, BindingResult bindingResult) {

        log.info("[UserCommandController] /signup API call");

        // 유효성 검사 실패 시 오류 처리
        if (bindingResult.hasErrors()) {

            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            });

            log.error("[UserCommandController] /signup error: {}", errorMessages.toString());

            throw new InvalidValueException(errorMessages.toString());
        }

        return userService.signup(userSignupRequest.toDomain());
    }


    @PostMapping("/signin")
    public ResponseEntity<String> signinUser(@RequestBody UserSigninRequest userSigninRequest, BindingResult bindingResult){

        log.info("[UserCommandController] /signin API call");

        // 유효성 검사 실패 시 오류 처리
        if (bindingResult.hasErrors()) {

            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            });

            log.error("[UserCommandController] /signin error: {}", errorMessages.toString());

            throw new InvalidValueException(errorMessages.toString());
        }

        String token = userService.signin(userSigninRequest.toDomain());

        // 응답에 토큰 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        return ResponseEntity.ok()
                .headers(headers)
                .body("signin successful");
    }

}
