package com.sparta.ordermanagement.application.domain.shop;


import com.sparta.ordermanagement.application.domain.user.User;

public class Shop {

    private Long id;
    private String uuid;
    private ShopCategory category;
    private String shopName;
    private double rating;

    private String ownerStringId;

    public Shop(Long id, String uuid, ShopCategory category, String shopName, double rating) {
        this.id = id;
        this.uuid = uuid;
        this.category = category;
        this.shopName = shopName;
        this.rating = rating;
    }

    public Shop(Long id, String uuid, ShopCategory category, String shopName, double rating, String ownerStringId) {
        this.id = id;
        this.uuid = uuid;
        this.category = category;
        this.shopName = shopName;
        this.rating = rating;
        this.ownerStringId = ownerStringId;
    }

    public boolean isSameCategory(String shopCategoryId) {
        return category.getId().equals(shopCategoryId);
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public ShopCategory getCategory() {
        return category;
    }

    public String getShopName() {
        return shopName;
    }

    public double getRating() {
        return rating;
    }

    public String getOwnerStringId() {
        return ownerStringId;
    }

    public boolean isOwnBy(User user) {
        return user.getUserStringId().equals(ownerStringId);
    }
}
