package com.sims.product.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReduceQuantityRequest {

    @NotNull
    private Integer warehouseId;

    @NotBlank
    private String sku;

    @Min(0)
    private Integer reduction;
}
