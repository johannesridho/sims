package com.sims.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(CreateProductRequest request) {
        final Product product = Product.builder()
                .sku(request.getSku())
                .name(request.getName())
                .description(request.getDescription())
                .quantity(request.getQuantity())
                .reserved(request.getReserved())
                .price(request.getPrice())
                .warehouseId(request.getWarehouseId())
                .build();

        return productRepository.save(product);
    }
}
