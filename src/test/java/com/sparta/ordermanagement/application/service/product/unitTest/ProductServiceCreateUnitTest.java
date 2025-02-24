package com.sparta.ordermanagement.application.service.product.unitTest;

import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.application.domain.product.ProductForCreate;
import com.sparta.ordermanagement.application.domain.user.User;
import com.sparta.ordermanagement.application.exception.shop.ShopDeletedException;
import com.sparta.ordermanagement.application.exception.shop.ShopUuidInvalidException;
import com.sparta.ordermanagement.application.exception.user.UserAccessDeniedException;
import com.sparta.ordermanagement.application.service.TestDataForUnitTest;
import com.sparta.ordermanagement.framework.persistence.entity.product.ProductState;
import com.sparta.ordermanagement.framework.persistence.entity.user.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceCreateUnitTest extends BaseProductServiceUnitTest {

    private String productUuid;
    private String productName;
    private int productPrice;
    private String productDescription;

    @BeforeEach
    void setUp() {
        super.setUp();
        productUuid = "product-uuid";
        productName = "후라이드";
        productPrice = 10_000;
        productDescription = "맛있는 후라이드";
    }

    @Test
    @DisplayName("[상품 생성 성공 단위 테스트] OWNER의 권한을 가지고 있는 사용자가 삭제되지 않는 가게의 상품을 생성할 경우 상품 식별자와 가게 식별자를 반환한다.")
    public void createProduct_successTest() {
        // Given
        Product expectedProduct = TestDataForUnitTest.createProduct(productUuid, productName,
            productPrice, shop);

        ProductForCreate productForCreate = new ProductForCreate(productName, productPrice,
            productDescription, ProductState.SHOW, shop.getUuid(), owner.getUserStringId(),
            owner.getRole());

        Mockito.when(productOutputPort.saveProduct(productForCreate)).thenReturn(expectedProduct);

        // When

        Product createdProduct = productService.createProduct(productForCreate);

        // Then
        Assertions.assertAll(
            () -> Assertions.assertNotNull(createdProduct),
            () -> Assertions.assertEquals(expectedProduct.getProductUuid(),
                createdProduct.getProductUuid()),
            () -> Assertions.assertEquals(expectedProduct.getShop().getUuid(),
                createdProduct.getShop().getUuid())
        );

        Mockito.verify(productOutputPort, Mockito.times(1)).saveProduct(productForCreate);
    }

    @Test
    @DisplayName("[상품 생성 실패 단위 테스트] OWNER 권한이 없는 사용자가 상품을 생성하면 예외를 발생시킨다.")
    public void createProduct_failureTest_notOwnerRole() {
        // Given
        User customer = TestDataForUnitTest.createUser("customer", Role.CUSTOMER, regionEntity);

        ProductForCreate productForCreate = new ProductForCreate(productName, productPrice,
            productDescription, ProductState.SHOW, shop.getUuid(), customer.getUserStringId(),
            customer.getRole());

        Mockito.doThrow(new UserAccessDeniedException(customer.getRole()))
            .when(userService)
            .validateOwnerRole(ArgumentMatchers.argThat(role -> role != Role.OWNER));

        // When & Then
        UserAccessDeniedException exception = Assertions.assertThrows(
            UserAccessDeniedException.class,
            () -> productService.createProduct(productForCreate)
        );

        Assertions.assertEquals(String.format("접근 권한이 없습니다.: %s", customer.getRole()),
            exception.getMessage());

        Mockito.verify(userService, Mockito.times(1)).validateOwnerRole(customer.getRole());
        Mockito.verifyNoInteractions(shopService, productOutputPort);
    }

    @Test
    @DisplayName("[상품 생성 실패 단위 테스트] OWNER 권한의 사용자가 유효하지 않는 가게의 식별자로 상품을 생성하려고 하는 경우 예외를 발생시킨다.")
    public void createProduct_failureTest_ownerRole_invalidShopUuid() {
        // Given
        String invalidShopUuid = "invalid-shop-uuid";

        ProductForCreate productForCreate = new ProductForCreate(productName, productPrice,
            productDescription, ProductState.SHOW, invalidShopUuid,
            owner.getUserStringId(),
            owner.getRole());

        Mockito.doThrow(new ShopUuidInvalidException(invalidShopUuid))
            .when(shopService).validateNotDeletedShopUuid(invalidShopUuid);

        // When & Then
        ShopUuidInvalidException exception = Assertions.assertThrows(
            ShopUuidInvalidException.class,
            () -> productService.createProduct(productForCreate)
        );

        Assertions.assertEquals(String.format("유효하지 않은 가게 식별자 입니다. : %s", invalidShopUuid),
            exception.getMessage());

        Mockito.verify(shopService, Mockito.times(1)).validateNotDeletedShopUuid(invalidShopUuid);
        Mockito.verifyNoInteractions(productOutputPort);
    }

    @Test
    @DisplayName("[상품 생성 실패 단위 테스트] OWNER 권한의 사용자가 삭제된 가게의 식별자로 상품을 생성하려고 하는 경우 예외를 발생시킨다.")
    public void createProduct_failureTest_ownerRole_deletedShopUuid() {
        // Given
        String deletedShopUuid = "deleted-shop-uuid";

        ProductForCreate productForCreate = new ProductForCreate(productName, productPrice,
            productDescription, ProductState.SHOW, deletedShopUuid,
            owner.getUserStringId(),
            owner.getRole());

        Mockito.doThrow(new ShopDeletedException(deletedShopUuid))
            .when(shopService).validateNotDeletedShopUuid(deletedShopUuid);

        // When & Then
        ShopDeletedException exception = Assertions.assertThrows(
            ShopDeletedException.class,
            () -> productService.createProduct(productForCreate)
        );

        Assertions.assertEquals(String.format("삭제된 가게 식별자 입니다. : %s", deletedShopUuid),
            exception.getMessage());

        Mockito.verify(shopService, Mockito.times(1)).validateNotDeletedShopUuid(deletedShopUuid);
        Mockito.verifyNoInteractions(productOutputPort);
    }
}