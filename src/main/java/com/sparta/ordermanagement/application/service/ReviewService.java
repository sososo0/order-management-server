package com.sparta.ordermanagement.application.service;

import com.sparta.ordermanagement.application.domain.order.Order;
import com.sparta.ordermanagement.application.domain.review.Review;
import com.sparta.ordermanagement.application.domain.review.ReviewForCreate;
import com.sparta.ordermanagement.application.domain.review.ReviewForDelete;
import com.sparta.ordermanagement.application.domain.review.ReviewForUpdate;
import com.sparta.ordermanagement.application.domain.shop.Shop;
import com.sparta.ordermanagement.application.exception.review.ReviewDeletedException;
import com.sparta.ordermanagement.application.exception.review.ReviewMismatchReviewerException;
import com.sparta.ordermanagement.application.exception.review.ReviewUuidInvalidException;
import com.sparta.ordermanagement.application.output.ReviewOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewOutputPort reviewOutputPort;
    private final OrderService orderService;
    private final ShopService shopService;

    public Review createReview(ReviewForCreate reviewForCreate) {

        Order order = orderService.validateOrderUuidAndGetNotDeletedOrder(reviewForCreate.orderUuid());
        Shop shop = shopService.validateShopUuidAndGetNotDeletedShop(order.getShopId());

        orderService.validateOrderBelongToUser(order, reviewForCreate.userStringId());

        return reviewOutputPort.saveReview(reviewForCreate, shop.getUuid());
    }

    public Review updateReview(ReviewForUpdate reviewForUpdate) {

        Order order = orderService.validateOrderUuidAndGetNotDeletedOrder(reviewForUpdate.orderUuid());
        shopService.validateNotDeletedShopUuid(order.getShopId());

        Review review = validateReviewUuidAndNotDeletedShopUuidAndGetReview(reviewForUpdate.reviewUuid());
        validateReviewBelongToUser(review, reviewForUpdate.userStringId());

        return reviewOutputPort.updateReview(reviewForUpdate);
    }

    public Review deleteReview(ReviewForDelete reviewForDelete) {
        Order order = orderService.validateOrderUuidAndGetNotDeletedOrder(reviewForDelete.orderUuid());
        shopService.validateNotDeletedShopUuid(order.getShopId());

        Review review = validateReviewUuidAndNotDeletedShopUuidAndGetReview(reviewForDelete.reviewUuid());
        validateReviewBelongToUser(review, reviewForDelete.userStringId());

        return reviewOutputPort.deleteReview(reviewForDelete);
    }

    private Review validateReviewUuidAndGet(String reviewUuid) {
        return reviewOutputPort.findByReviewUuid(reviewUuid)
            .orElseThrow(() -> new ReviewUuidInvalidException(reviewUuid));
    }

    private void validateReviewUuidAndNotDeletedShopUuid(String reviewUuid) {
        if(validateReviewUuidAndGet(reviewUuid).getIsDeleted()) {
            throw new ReviewDeletedException(reviewUuid);
        }
    }

    public Review validateReviewUuidAndNotDeletedShopUuidAndGetReview(String reviewUuid) {
        Review review = validateReviewUuidAndGet(reviewUuid);
        if(review.getIsDeleted()) {
            throw new ReviewDeletedException(reviewUuid);
        }
        return review;
    }

    private void validateReviewBelongToUser(Review review, String userStringId) {
        if (!review.isSameReviewer(userStringId)) {
            throw new ReviewMismatchReviewerException(userStringId);
        }
    }
}
