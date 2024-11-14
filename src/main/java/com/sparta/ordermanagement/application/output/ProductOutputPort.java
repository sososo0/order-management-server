package com.sparta.ordermanagement.application.output;

import com.sparta.ordermanagement.application.domain.product.Product;
import com.sparta.ordermanagement.application.domain.product.ProductForCreate;
import com.sparta.ordermanagement.application.domain.product.ProductForDelete;
import com.sparta.ordermanagement.application.domain.product.ProductForUpdate;
import com.sparta.ordermanagement.application.domain.product.ProductStateForUpdate;
import java.util.Optional;

public interface ProductOutputPort {

    Product saveProduct(ProductForCreate productForCreate);

    Product updateProductState(ProductStateForUpdate productStateForUpdate);

    Optional<Product> findByProductUuid(String productUuid);

    Product deleteProduct(ProductForDelete productForDelete);

    Product updateProduct(ProductForUpdate productForUpdate);
}
