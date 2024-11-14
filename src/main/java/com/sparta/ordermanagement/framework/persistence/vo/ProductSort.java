package com.sparta.ordermanagement.framework.persistence.vo;

import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductSort implements Sort {

    CREATED_AT("created_at", LocalDate.class),
    PRODUCT_NAME("product_name", String.class);

    private String columnName;
    private Class<?> valueType;

    public static ProductSort of(String baseColumn) {
        for (ProductSort productSort : ProductSort.values()) {
            if (Objects.equals(productSort.columnName, baseColumn)) {
                return productSort;
            }
        }

        return CREATED_AT;
    }

    @Override
    public boolean isDefaultSort() {
        return Objects.equals(columnName, Sort.DEFAULT_SORT);
    }
}
