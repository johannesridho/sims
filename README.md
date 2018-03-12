# Sims API
Simple Inventory Management System API

## Technology Stack
```
- Spring Framework 4
- Java 8
- MySQL 5.7
```

## How to Run
```
1. clone this repository
2. install JDK 1.8
3. install MySQL 5.7
    - run at localhost:3306 
    - create databases with name 'sims' and 'sims-test'
    - create user with name 'sims' and password 'sims' and grant it access to sims and sims-test database
4. run ./gradlew bootRun
``` 

## API Documentation

### Create Product
Create/register product with SKU
``` 
POST /products
Request body :
{
  "sku": "sku",
  "name": "name",
  "description": "desc",
  "warehouse_id":1,
  "quantity": 10,
  "reserved": 0,
  "price": 100000
}
```
```
Response :
{
    "id": 1,
    "sku": "sku",
    "name": "name",
    "description": "desc",
    "warehouse_id": 1,
    "quantity": 10,
    "reserved": 0,
    "price": 100000,
    "created_at": "2018-03-11 19:23:06",
    "updated_at": "2018-03-11 19:23:06"
}
```

### Get Products By Warehouse Id
Get current stocks on hand (warehouse inventory) in certain warehouse, including current pending shipped inventory
```
GET /products?warehouse_id=1
```
```
Response :
[
    {
        "id": 1,
        "sku": "sku",
        "name": "name",
        "description": "desc",
        "warehouse_id": 1,
        "quantity": 10,
        "reserved": 0,
        "price": 100000,
        "created_at": "2018-03-11 19:22:45",
        "updated_at": "2018-03-11 19:22:45"
    },
    {
        "id": 2,
        "sku": "sku2",
        "name": "name2",
        "description": "desc2",
        "warehouse_id": 1,
        "quantity": 10,
        "reserved": 5,
        "price": 100000,
        "created_at": "2018-03-11 19:22:52",
        "updated_at": "2018-03-11 19:22:52"
    }
]
```

### Get Reserved Products By Warehouse Id
Get current pending shipped inventory in certain warehouse
```
GET /products/reserved?warehouse_id=1
```
```
Response :
[
    {
        "id": 2,
        "sku": "sku2",
        "name": "name2",
        "description": "desc2",
        "warehouse_id": 1,
        "quantity": 10,
        "reserved": 5,
        "price": 100000,
        "created_at": "2018-03-11 19:22:52",
        "updated_at": "2018-03-11 19:22:52"
    }
]
```

### Add Quantity
Make addition to inventory (when warehouse receives inventory)
```
PATCH /products/quantity/add
Request body :
{
  "sku": "sku",
  "warehouse_id": 1,
  "addition": 2
}
```
```
Response :
[
    {
        "id": 1,
        "sku": "sku",
        "name": "name",
        "description": "desc",
        "warehouse_id": 1,
        "quantity": 12,
        "reserved": 0,
        "price": 100000,
        "created_at": "2018-03-11 19:22:52",
        "updated_at": "2018-03-11 19:22:52"
    }
]
```

### Reduce Quantity
Reduction of inventory (when warehouse ships items or manual warehouse adjustments)
```
PATCH /products/quantity/reduce
Request body :
{
  "sku": "sku",
  "warehouse_id": 1,
  "reduction": 1
}
```
```
Response :
[
    {
        "id": 1,
        "sku": "sku",
        "name": "name",
        "description": "desc",
        "warehouse_id": 1,
        "quantity": 11,
        "reserved": 0,
        "price": 100000,
        "created_at": "2018-03-11 19:22:52",
        "updated_at": "2018-03-11 19:22:52"
    }
]
```

### Add Reserved Products
Add reservation of inventory (when customer makes a purchase but item is not yet shipped from the warehouse)
```
PATCH /products/reserved/add
Request body :
{
  "sku": "sku",
  "warehouse_id": 1,
  "addition": 2
}
```
```
Response :
[
    {
        "id": 1,
        "sku": "sku",
        "name": "name",
        "description": "desc",
        "warehouse_id": 1,
        "quantity": 11,
        "reserved": 2,
        "price": 100000,
        "created_at": "2018-03-11 19:22:52",
        "updated_at": "2018-03-11 19:22:52"
    }
]
```

### Reduce Reserved Products
Reduce reservation of inventory (when the reserved products shipped from the warehouse). Quantity and reserved value
will be reduced
```
PATCH /products/reserved/reduce
Request body :
{
  "sku": "sku",
  "warehouse_id": 1,
  "reduction": 1
}
```
```
Response :
[
    {
        "id": 1,
        "sku": "sku",
        "name": "name",
        "description": "desc",
        "warehouse_id": 1,
        "quantity": 10,
        "reserved": 1,
        "price": 100000,
        "created_at": "2018-03-11 19:22:52",
        "updated_at": "2018-03-11 19:22:52"
    }
]
```