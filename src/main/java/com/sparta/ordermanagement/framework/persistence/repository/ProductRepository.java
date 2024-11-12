package com.sparta.ordermanagement.framework.persistence.repository;

import com.sparta.ordermanagement.framework.persistence.entity.product.ProductEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByProductUuid(String productUuid);
}
