package com.sims.product;

import com.sims.exception.NotFoundException;
import com.sims.exception.ReductionTooLargeException;
import com.sims.exception.ReservationTooLargeException;
import com.sims.exception.ReservedReductionTooLargeException;
import com.sims.product.request.CreateProductRequest;
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

    public Product addQuantity(Integer warehouseId, String sku, Integer addition) {
        final Product product = getByWarehouseIdAndSku(warehouseId, sku);
        product.setQuantity(product.getQuantity() + addition);

        return productRepository.save(product);
    }

    public Product reduceQuantity(Integer warehouseId, String sku, Integer reduction) {
        final Product product = getByWarehouseIdAndSku(warehouseId, sku);

        if (reduction > product.getQuantity()) {
            throw new ReductionTooLargeException();
        }

        product.setQuantity(product.getQuantity() - reduction);

        return productRepository.save(product);
    }

    public Product addReservedProducts(Integer warehouseId, String sku, Integer addition) {
        final Product product = getByWarehouseIdAndSku(warehouseId, sku);

        if (addition + product.getReserved() > product.getQuantity()) {
            throw new ReservationTooLargeException();
        }

        product.setReserved(product.getReserved() + addition);

        return productRepository.save(product);
    }

    public Product reduceReservedProducts(Integer warehouseId, String sku, Integer reduction) {
        final Product product = getByWarehouseIdAndSku(warehouseId, sku);

        if (reduction > product.getReserved()) {
            throw new ReservedReductionTooLargeException();
        }

        product.setReserved(product.getReserved() - reduction);
        product.setQuantity(product.getQuantity() - reduction);

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

    private Product getByWarehouseIdAndSku(Integer warehouseId, String sku) {
        return productRepository.findByWarehouseIdAndSku(warehouseId, sku)
                .orElseThrow(() -> new NotFoundException(String.format("Product with warehouseId $s and sku $s " +
                        "is not found.", warehouseId, sku)));
    }

    private void validateWarehouseId(Integer id) {
        warehouseRepository.findOne(id)
                .orElseThrow(() -> new NotFoundException(Warehouse.class, id.toString()));
    }
}
