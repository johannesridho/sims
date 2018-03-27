package com.sims.product;

import com.sims.jpa.Jpa8Repository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Jpa8Repository<Product, Integer> {
    List<Product> findByWarehouseIdAndQuantityNot(Integer warehouseId, Integer value);
    List<Product> findByWarehouseIdAndReservedNot(Integer warehouseId, Integer value);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findByWarehouseIdAndSku(Integer warehouseId, String sku);
}
