package com.sparta.ordermanagement.bootstrap.rest.controller;

import com.sparta.ordermanagement.application.exception.InvalidValueException;
import com.sparta.ordermanagement.application.service.UserService;
import com.sparta.ordermanagement.bootstrap.rest.dto.user.UserSignupRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void signinUser(){

    }

}
