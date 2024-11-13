package com.sparta.ordermanagement.framework.persistence.repository;

import static com.sparta.ordermanagement.framework.persistence.entity.product.QProductEntity.productEntity;
import static com.sparta.ordermanagement.framework.persistence.entity.shop.QShopEntity.shopEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.ordermanagement.framework.persistence.entity.product.ProductEntity;
import com.sparta.ordermanagement.framework.persistence.vo.Cursor;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductQueryRepository {

    private static final Long DEFAULT_PRODUCT_ID = 0L;

    private final JPAQueryFactory jpaQueryFactory;

    public List<ProductEntity> findAllByShopUuid(String shopUuid, Cursor cursor) {

        Long basedProductId = getBasedProductId(cursor);

        if (cursor.isDefaultSort()) {
            return findAllByShopUuidByCreatedAt(shopUuid, basedProductId, cursor.size());
        }
        return findAllByShopUuidByProductName(shopUuid, basedProductId, cursor.size());
    }

    private Long getBasedProductId(Cursor cursor) {
        if (cursor.isFirstPage()) {
            return DEFAULT_PRODUCT_ID;
        }

        return Optional.ofNullable(jpaQueryFactory.select(productEntity)
                .from(productEntity)
                .where(productEntity.productUuid.eq(cursor.basedUuid()))
                .fetchOne())
            .orElseThrow(IllegalArgumentException::new)
            .getId();
    }

    /*
    select *
    from p_product p
    inner join p_shop s on p.shop_entity_shop_id = s.shop_id
    where s.uuid = :shopUuid
      and p.product_id > :basedProductId
    order by p.product_id asc, p.created_at asc
    limit :size;
    */
    private List<ProductEntity> findAllByShopUuidByCreatedAt(String shopUuid, Long basedProductId, int size) {
        return jpaQueryFactory.selectFrom(productEntity)
            .innerJoin(shopEntity)
            .on(shopEntity.id.eq(productEntity.shopEntity.id))
            .where(
                shopEntity.shopUuid.eq(shopUuid)
                    .and(productEntity.id.gt(basedProductId))
            )
            .orderBy(productEntity.id.asc(), productEntity.createdAt.asc())
            .limit(size)
            .fetch();
    }

    /*
    select *
    from p_product p
    inner join p_shop s on p.shop_entity_shop_id = s.shop_id
    where s.uuid = :shopUuid
      and p.product_id > :basedProductId
    order by p.product_id asc, p.product_name asc
    limit :size;
    */
    private List<ProductEntity> findAllByShopUuidByProductName(String shopUuid, Long basedProductId, int size) {
        return jpaQueryFactory.selectFrom(productEntity)
            .innerJoin(shopEntity)
            .on(shopEntity.id.eq(productEntity.shopEntity.id))
            .where(
                shopEntity.shopUuid.eq(shopUuid)
                    .and(productEntity.id.gt(basedProductId))
            )
            .orderBy(productEntity.id.asc(), productEntity.productName.asc())
            .limit(size)
            .fetch();
    }
}
