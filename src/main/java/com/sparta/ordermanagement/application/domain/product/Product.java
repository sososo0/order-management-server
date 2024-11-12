package com.sparta.ordermanagement.application.domain.product;

import com.sparta.ordermanagement.application.domain.shop.Shop;
import com.sparta.ordermanagement.framework.persistence.entity.product.ProductState;

public class Product {

    private Long id;
    private String productUuid;
    private String productName;
    private Integer productPrice;
    private String productDescription;
    private ProductState productState;
    private Shop shop;

    public Product(
        Long id,
        String productUuid,
        String productName,
        Integer productPrice,
        String productDescription,
        ProductState productState,
        Shop shop
    ) {
        this.id = id;
        this.productUuid = productUuid;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productState = productState;
        this.shop = shop;
    }

    public boolean isSameShop(String shopUuid) {
        return shop.getUuid().equals(shopUuid);
    }

    public Long getId() {
        return id;
    }

    public String getProductUuid() {
        return productUuid;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public ProductState getProductState() {
        return productState;
    }

    public Shop getShop() {
        return shop;
    }
}
