package com.sims.product.request;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@RequiredArgsConstructor
public class CreateProductRequest {

    @NotBlank
    private final String sku;

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotNull
    private final Integer warehouseId;

    @NotNull
    private final Integer quantity;

    @NotNull
    private final Integer reserved;

    @NotNull
    private final BigDecimal price;
}
