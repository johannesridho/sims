package com.sims.product;

import com.sims.product.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Validated @RequestBody CreateProductRequest request) {
        return productService.create(request);
    }

    @PatchMapping("quantity/add")
    public Product addQuantity(@Validated @RequestBody AddQuantityRequest request) {
        return productService.addQuantity(request.getWarehouseId(), request.getSku(), request.getAddition());
    }

    @PatchMapping("quantity/reduce")
    public Product reduceQuantity(@Validated @RequestBody ReduceQuantityRequest request) {
        return productService.reduceQuantity(request.getWarehouseId(), request.getSku(), request.getReduction());
    }

    @PatchMapping("reserved/add")
    public Product addReservedProducts(@Validated @RequestBody AddReservedRequest request) {
        return productService.addReservedProducts(request.getWarehouseId(), request.getSku(),
                request.getAddition());
    }

    @PatchMapping("reserved/reduce")
    public Product reduceReservedProducts(@Validated @RequestBody ReduceReservedRequest request) {
        return productService.reduceReservedProducts(request.getWarehouseId(), request.getSku(),
                request.getReduction());
    }

    @GetMapping
    public List<Product> getByWarehouse(@RequestParam(name = "warehouse_id") Integer warehouseId) {
        return productService.getByWarehouse(warehouseId);
    }

    @GetMapping("reserved")
    public List<Product> getReservedProductByWarehouse(@RequestParam(name = "warehouse_id") Integer warehouseId) {
        return productService.getReservedProductsByWarehouse(warehouseId);
    }
}
