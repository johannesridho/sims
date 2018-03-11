package com.sims.product;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product create(@Validated @RequestBody CreateProductRequest request) {
        return productService.create(request);
    }

    @GetMapping()
    public List<Product> getByWarehouse(@RequestParam(name = "warehouse_id") Integer warehouseId) {
        return productService.getByWarehouse(warehouseId);
    }

    @GetMapping("reserved")
    public List<Product> getReservedProductByWarehouse(@RequestParam(name = "warehouse_id") Integer warehouseId) {
        return productService.getReservedProductsByWarehouse(warehouseId);
    }
}
