package com.sims.product.request;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@RequiredArgsConstructor
public class AddQuantityRequest {

    @NotNull
    private final Integer warehouseId;

    @NotBlank
    private final String sku;

    @Min(0)
    private final Integer addition;
}
