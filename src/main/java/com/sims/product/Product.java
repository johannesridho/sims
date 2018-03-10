package com.sims.product;

import com.sims.jpa.converter.ZonedDateTimeConverter;
import com.sims.warehouse.Warehouse;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    private Long id;
    private String sku;
    private String name;
    private String description;
    private int quantity;
    private int reserved;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    @Convert(converter = ZonedDateTimeConverter.class)
    @CreatedDate
    private ZonedDateTime createdAt;

    @Convert(converter = ZonedDateTimeConverter.class)
    @LastModifiedDate
    private ZonedDateTime updatedAt;

}
