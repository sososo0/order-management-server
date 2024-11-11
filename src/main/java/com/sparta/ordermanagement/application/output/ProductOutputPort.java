package com.sparta.ordermanagement.application.output;

import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.application.domain.product.ProductForCreate;

public interface ProductOutputPort {

    Product saveProduct(ProductForCreate productForCreate);
}
