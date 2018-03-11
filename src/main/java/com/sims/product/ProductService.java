package com.sims.product;

import com.sims.exception.NotFoundException;
import com.sims.warehouse.Warehouse;
import com.sims.warehouse.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public Product create(CreateProductRequest request) {
        final Product product = new Product();
        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setReserved(request.getReserved());
        product.setPrice(request.getPrice());
        product.setWarehouseId(request.getWarehouseId());

        return productRepository.save(product);
    }

    public List<Product> getByWarehouse(Integer warehouseId) {
        validateWarehouseId(warehouseId);

        return productRepository.findByWarehouseIdAndQuantityNot(warehouseId, 0);
    }

    public List<Product> getReservedProductsByWarehouse(Integer warehouseId) {
        validateWarehouseId(warehouseId);

        return productRepository.findByWarehouseIdAndReservedNot(warehouseId, 0);
    }

    private void validateWarehouseId(Integer id) {
        warehouseRepository.findOne(id)
                .orElseThrow(() -> new NotFoundException(Warehouse.class, id.toString()));
    }
}
