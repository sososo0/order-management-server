package com.sparta.ordermanagement.application.service;

import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.application.domain.product.ProductForCreate;
import com.sparta.ordermanagement.application.output.ProductOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductOutputPort productOutputPort;
    private final ShopService shopService;

    public Product createProduct(ProductForCreate productForCreate) {
        shopService.validateShopUuid(productForCreate.shopUuid());
        return productOutputPort.saveProduct(productForCreate);
    }


}
