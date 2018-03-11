package com.sims.product;

import com.sims.jpa.Jpa8Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Jpa8Repository<Product, Integer> {
    List<Product> findByWarehouseIdAndQuantityNot(Integer warehouseId, Integer value);
    List<Product> findByWarehouseIdAndReservedNot(Integer warehouseId, Integer value);
    Optional<Product> findByWarehouseIdAndSku(Integer warehousId, String sku);
}
