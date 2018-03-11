package com.sims.product;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@RequiredArgsConstructor
public class CreateProductRequest {

    private final String sku;
    private final String name;
    private final String description;
    private final Integer warehouseId;
    private final Integer quantity;
    private final Integer reserved;
    private final BigDecimal price;
}
