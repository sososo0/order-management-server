package com.sparta.ordermanagement.bootstrap.admin.controller;

import com.sparta.ordermanagement.application.admin.AdminUserService;
import com.sparta.ordermanagement.application.exception.InvalidValueException;
import com.sparta.ordermanagement.bootstrap.admin.dto.UserAdminSignupRequest;
import com.sparta.ordermanagement.bootstrap.auth.UserDetailsImpl;
import com.sparta.ordermanagement.bootstrap.rest.exception.exceptions.InvalidAuthorizationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/v1/users")
@Slf4j
public class AdminUserController {

    private final AdminUserService adminUserService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String createAdmin(@RequestBody UserAdminSignupRequest userAdminSignupRequest, BindingResult bindingResult, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        log.info("[AdminUserController] /admin/v1/users call");

        if(userDetails.getAuthorities() == null || userDetails.getAuthorities().isEmpty()) {
            throw new InvalidAuthorizationException("Authorities cannot be null or empty");
        }

        // 유효성 검사 실패 시 오류 처리
        if (bindingResult.hasErrors()) {

            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            });

            log.error("[AdminUserController] /admin/v1/users error: {}", errorMessages.toString());

            throw new InvalidValueException(errorMessages.toString());
        }

        return adminUserService.signup(userAdminSignupRequest.toDomain());

    }
}
