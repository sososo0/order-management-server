package com.sparta.ordermanagement.framework.persistence.adapter;

import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.application.domain.product.ProductForCreate;
import com.sparta.ordermanagement.application.exception.shop.ShopUuidInvalidException;
import com.sparta.ordermanagement.application.output.ProductOutputPort;
import com.sparta.ordermanagement.framework.persistence.entity.product.ProductEntity;
import com.sparta.ordermanagement.framework.persistence.entity.shop.ShopEntity;
import com.sparta.ordermanagement.framework.persistence.repository.ProductRepository;
import com.sparta.ordermanagement.framework.persistence.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductPersistenceAdapter implements ProductOutputPort {

    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;


    @Override
    public Product saveProduct(ProductForCreate productForCreate) {

        ShopEntity shopEntity = shopRepository.findByShopUuid(productForCreate.shopUuid())
            .orElseThrow(() -> new ShopUuidInvalidException(productForCreate.shopUuid()));

        return productRepository.save(ProductEntity.from(productForCreate, shopEntity)).toDomain();
    }
}
