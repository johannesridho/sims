package com.sims.product;

import com.sims.jpa.Jpa8Repository;

import java.util.List;

public interface ProductRepository extends Jpa8Repository<Product, Integer> {
    List<Product> findByWarehouseIdAndQuantityNot(Integer warehouseId, Integer value);
    List<Product> findByWarehouseIdAndReservedNot(Integer warehouseId, Integer value);

}
