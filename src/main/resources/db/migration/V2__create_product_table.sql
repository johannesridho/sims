CREATE TABLE IF NOT EXISTS product (
    id INT(10) UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT,
    sku VARCHAR(64) NOT NULL,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(256),
    quantity INT(10) UNSIGNED DEFAULT 0,
    reserved INT(10) UNSIGNED DEFAULT 0,
    price decimal(12,2) UNSIGNED NOT NULL,
    warehouse_id INT(10) UNSIGNED NOT NULL,
    created_at BIGINT UNSIGNED NOT NULL,
    updated_at BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (warehouse_id) REFERENCES warehouse(id) ON UPDATE CASCADE ON DELETE CASCADE,
    UNIQUE KEY sku_warehouse (sku, warehouse_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;