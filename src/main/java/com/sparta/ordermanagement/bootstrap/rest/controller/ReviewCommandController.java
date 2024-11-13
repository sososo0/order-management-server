package com.sparta.ordermanagement.bootstrap.rest.controller;

import com.sparta.ordermanagement.application.domain.review.Review;
import com.sparta.ordermanagement.application.domain.review.ReviewForCreate;
import com.sparta.ordermanagement.application.service.ReviewService;
import com.sparta.ordermanagement.bootstrap.rest.dto.review.ReviewCreateRequest;
import com.sparta.ordermanagement.bootstrap.rest.dto.review.ReviewCreateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders/{orderUuid}/reviews")
public class ReviewCommandController {

    // TODO: 추후에 지울 예정
    private static final String TEST_CREATED_USER_ID = "0000";

    // TODO: Order에 shop_id 필드가 있다고 가정

    private final ReviewService reviewService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReviewCreateResponse createReview(
        @PathVariable(value = "orderUuid") String orderUuid,
        @Valid @RequestBody ReviewCreateRequest reviewCreateRequest
    ) {

        // TODO: 작성자가 주문한 사람과 동일한 지 확인

        ReviewForCreate reviewForCreate = reviewCreateRequest.toDomain(
            orderUuid,
            TEST_CREATED_USER_ID
        );
        Review review = reviewService.createReview(reviewForCreate);

        return ReviewCreateResponse.from(review);
    }


}
