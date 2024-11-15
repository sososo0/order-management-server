package com.sparta.ordermanagement.bootstrap.admin.controller;

import com.sparta.ordermanagement.application.admin.AdminUserService;
import com.sparta.ordermanagement.application.exception.InvalidValueException;
import com.sparta.ordermanagement.bootstrap.admin.dto.UserAdminSignupRequest;
import com.sparta.ordermanagement.bootstrap.admin.dto.UserGetResponse;
import com.sparta.ordermanagement.bootstrap.auth.UserDetailsImpl;
import com.sparta.ordermanagement.bootstrap.rest.exception.exceptions.InvalidAuthorizationException;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/v1/users")
@Slf4j
public class AdminUserController {

    private final AdminUserService adminUserService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String createAdmin(
            @RequestBody UserAdminSignupRequest userAdminSignupRequest, BindingResult bindingResult,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

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


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<UserGetResponse> getUsers(@AuthenticationPrincipal UserDetailsImpl userDetails){

        log.info("[AdminUserController]-[getUsers] /admin/v1/users call");

        return adminUserService.getUsers().stream()
                .map(UserGetResponse::from)
                .toList();
    }

    @PatchMapping("/{user_string_id}/{role}")
    @ResponseStatus(HttpStatus.OK)
    public Map.Entry updateUser(
            @PathVariable("user_string_id") String userStringId,
            @PathVariable("role") Role role,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        log.info("[AdminUserController]-[updateUser] /admin/v1/users/{user_string_id} call");
        Integer updatedUserId = adminUserService.updateUser(userStringId, role);

        return Map.entry("userId", updatedUserId);
    }

    @DeleteMapping("/{user_string_id}")
    @ResponseStatus(HttpStatus.OK)
    public Map.Entry deleteUser(
            @PathVariable("user_string_id") String userStringId,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        log.info("[AdminUserController]-[deleteUser] /admin/v1/users/{} call", userStringId);

        adminUserService.deleteUser(userStringId);

        return Map.entry("userId", userStringId);
    }

}
