package com.sims.product;

import com.sims.FunctionalTest;
import com.sims.product.request.*;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductFunctionalTest extends FunctionalTest {

    @Test
    public void testCreate_Success() {
        final CreateProductRequest request = new CreateProductRequest("sku", "name", "desc",
                1, 10, 2, new BigDecimal("100000"));

        final Map<String, Object> response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
            .when()
                .post("/products")
            .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("sku", equalTo("sku"))
                .body("name", equalTo("name"))
                .body("description", equalTo("desc"))
                .body("warehouse_id", equalTo(1))
                .body("quantity", equalTo(10))
                .body("reserved", equalTo(2))
                .body("price", equalTo(100000))
                .body("created_at", notNullValue())
                .body("updated_at", notNullValue())
            .extract().as(Map.class);

        assertThat(response).containsOnlyKeys(
            "id", "sku", "name", "description", "warehouse_id", "quantity", "reserved", "price", "created_at",
                "updated_at");
    }

    @Test
    public void testAddQuantity_Success() {
        testHelper.createProducts();

        final AddQuantityRequest request = new AddQuantityRequest(1, "sku", 2);

        final Map<String, Object> response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
            .when()
                .patch("/products/quantity/add")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("sku", equalTo("sku"))
                .body("name", equalTo("name"))
                .body("description", equalTo("desc"))
                .body("warehouse_id", equalTo(1))
                .body("quantity", equalTo(12))
                .body("reserved", equalTo(2))
                .body("price", equalTo(100000f))
                .body("created_at", notNullValue())
                .body("updated_at", notNullValue())
                .extract().as(Map.class);

        assertThat(response).containsOnlyKeys(
                "id", "sku", "name", "description", "warehouse_id", "quantity", "reserved", "price", "created_at",
                "updated_at");
    }

    @Test
    public void testReduceQuantity_Success() {
        testHelper.createProducts();

        final ReduceQuantityRequest request = new ReduceQuantityRequest(1, "sku", 2);

        final Map<String, Object> response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
            .when()
                .patch("/products/quantity/reduce")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("sku", equalTo("sku"))
                .body("name", equalTo("name"))
                .body("description", equalTo("desc"))
                .body("warehouse_id", equalTo(1))
                .body("quantity", equalTo(8))
                .body("reserved", equalTo(2))
                .body("price", equalTo(100000f))
                .body("created_at", notNullValue())
                .body("updated_at", notNullValue())
                .extract().as(Map.class);

        assertThat(response).containsOnlyKeys(
                "id", "sku", "name", "description", "warehouse_id", "quantity", "reserved", "price", "created_at",
                "updated_at");
    }

    @Test
    public void testReduceQuantity_ReductionTooLarge() {
        testHelper.createProducts();

        final ReduceQuantityRequest request = new ReduceQuantityRequest(1, "sku", 20);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
            .when()
                .patch("/products/quantity/reduce")
            .then()
                .statusCode(400)
                .body("error", equalTo("reduction_too_large"))
                .body("error_description", equalTo("Reduction value must be the same or less than " +
                        "the available product quantity"));
    }

    @Test
    public void testAddReservedProducts_Success() {
        testHelper.createProducts();

        final AddReservedRequest request = new AddReservedRequest(1, "sku", 2);

        final Map<String, Object> response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
            .when()
                .patch("/products/reserved/add")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("sku", equalTo("sku"))
                .body("name", equalTo("name"))
                .body("description", equalTo("desc"))
                .body("warehouse_id", equalTo(1))
                .body("quantity", equalTo(10))
                .body("reserved", equalTo(4))
                .body("price", equalTo(100000f))
                .body("created_at", notNullValue())
                .body("updated_at", notNullValue())
                .extract().as(Map.class);

        assertThat(response).containsOnlyKeys(
                "id", "sku", "name", "description", "warehouse_id", "quantity", "reserved", "price", "created_at",
                "updated_at");
    }

    @Test
    public void testAddReservedProducts_ReservationTooLarge() {
        testHelper.createProducts();

        final AddReservedRequest request = new AddReservedRequest(1, "sku", 20);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
            .when()
                .patch("/products/reserved/add")
            .then()
                .statusCode(400)
                .body("error", equalTo("reservation_too_large"))
                .body("error_description", equalTo("Reservation value + current reserved value " +
                        "must be the same or less than the available product quantity"));
    }

    @Test
    public void testReduceReservedProducts_Success() {
        testHelper.createProducts();

        final ReduceReservedRequest request = new ReduceReservedRequest(1, "sku", 2);

        final Map<String, Object> response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
            .when()
                .patch("/products/reserved/reduce")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("sku", equalTo("sku"))
                .body("name", equalTo("name"))
                .body("description", equalTo("desc"))
                .body("warehouse_id", equalTo(1))
                .body("quantity", equalTo(8))
                .body("reserved", equalTo(0))
                .body("price", equalTo(100000f))
                .body("created_at", notNullValue())
                .body("updated_at", notNullValue())
                .extract().as(Map.class);

        assertThat(response).containsOnlyKeys(
                "id", "sku", "name", "description", "warehouse_id", "quantity", "reserved", "price", "created_at",
                "updated_at");
    }

    @Test
    public void testReduceReservedProducts_ReservedReductionTooLarge() {
        testHelper.createProducts();

        final ReduceReservedRequest request = new ReduceReservedRequest(1, "sku", 20);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
            .when()
                .patch("/products/reserved/reduce")
            .then()
                .statusCode(400)
                .body("error", equalTo("reserved_reduction_too_large"))
                .body("error_description", equalTo("Reduction value must be the same or less than " +
                        "the reserved product"));
    }

    @Test
    public void testGetByWarehouse_Success() {
        testHelper.createProducts();

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/products?warehouse_id=1")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", hasItem(notNullValue()))
                .body("sku", hasItems("sku", "sku2"))
                .body("name", hasItems("name", "name2"))
                .body("description", hasItems("desc", "desc2"))
                .body("warehouse_id", hasItems(1))
                .body("quantity", hasItems(10, 20))
                .body("reserved", hasItems(2, 0))
                .body("price", hasItems(100000f, 200000f))
                .body("created_at", hasItem(notNullValue()))
                .body("updated_at", hasItem(notNullValue()));
    }

    @Test
    public void testGetReservedProductsByWarehouse_Success() {
        testHelper.createProducts();

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/products/reserved?warehouse_id=1")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", hasItem(notNullValue()))
                .body("sku", hasItems("sku"))
                .body("name", hasItems("name"))
                .body("description", hasItems("desc"))
                .body("warehouse_id", hasItems(1))
                .body("quantity", hasItems(10))
                .body("reserved", hasItems(2))
                .body("price", hasItems(100000f))
                .body("created_at", hasItem(notNullValue()))
                .body("updated_at", hasItem(notNullValue()));
    }
}