package com.sparta.ordermanagement.bootstrap.rest.controller;

import com.sparta.ordermanagement.application.domain.shop.ShopForUpdate;
import com.sparta.ordermanagement.application.service.ShopService;
import com.sparta.ordermanagement.bootstrap.auth.UserDetailsImpl;
import com.sparta.ordermanagement.bootstrap.rest.dto.shop.ShopUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/shops")
public class ShopCommandController {

    private final ShopService shopService;

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{shopId}")
    public String updateShop(
        @PathVariable(name = "shopId") String shopId,
        @RequestBody ShopUpdateRequest shopUpdateRequest,
        @AuthenticationPrincipal UserDetailsImpl user) {

        ShopForUpdate shopForUpdate = shopUpdateRequest.toDomain(shopId, user.getUserStringId());

        return shopService.updateShop(shopForUpdate);
    }
}
