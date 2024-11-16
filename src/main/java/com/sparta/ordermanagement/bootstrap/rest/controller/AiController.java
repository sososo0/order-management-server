package com.sparta.ordermanagement.bootstrap.rest.controller;

import com.sparta.ordermanagement.application.service.AiService;
import com.sparta.ordermanagement.bootstrap.auth.UserDetailsImpl;
import com.sparta.ordermanagement.bootstrap.rest.dto.ai.AiRecommendProductNameRequest;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/ai")
    @ResponseStatus(HttpStatus.OK)
    public Map.Entry aiRecommendProductName(
            @RequestBody AiRecommendProductNameRequest aiRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            BindingResult bindingResult
    ) {

        if(userDetails.getRole() == Role.CUSTOMER){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "API 접근 권한이 없습니다.");
        }

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().toString());
        }

        String aiResponse = aiService.aiRecommendProductName(
                aiRequest.toDomain(userDetails.getUserStringId()),
                userDetails.getUserStringId());
        return Map.entry("responseContent", aiResponse);
    }
}
