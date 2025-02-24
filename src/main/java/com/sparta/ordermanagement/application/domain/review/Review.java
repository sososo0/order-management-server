package com.sparta.ordermanagement.application.domain.review;

import com.sparta.ordermanagement.application.domain.shop.Shop;
import com.sparta.ordermanagement.application.domain.user.User;
import java.time.LocalDateTime;

public class Review {

    private Long id;
    private String reviewUuid;
    private Integer rating;
    private String content;
    private Shop shop;
    private User user;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    public Review(
        Long id,
        String reviewUuid,
        Integer rating,
        String content,
        Shop shop,
        User user,
        boolean isDeleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy
    ) {
        this.id = id;
        this.reviewUuid = reviewUuid;
        this.rating = rating;
        this.content = content;
        this.shop = shop;
        this.user = user;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public Review(
        Long id,
        String reviewUuid,
        Integer rating,
        String content,
        Shop shop,
        User user,
        boolean isDeleted
    ) {
        this.id = id;
        this.reviewUuid = reviewUuid;
        this.rating = rating;
        this.content = content;
        this.shop = shop;
        this.user = user;
        this.isDeleted = isDeleted;
    }

    public boolean isSameReviewer(String userStringId) {
        return user.getUserStringId().equals(userStringId);
    }

    public Long getId() {
        return id;
    }

    public String getReviewUuid() {
        return reviewUuid;
    }

    public Integer getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public Shop getShop() {
        return shop;
    }

    public User getUser() {
        return user;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }
}
