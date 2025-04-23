package com.kokwai.catalogservice.web.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.kokwai.catalogservice.AbstractIT;
import com.kokwai.catalogservice.domain.product.Product;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Test
    void shouldBeReturningProducts() {
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(3))
                .body("totalElements", is(3))
                .body("totalPages", is(1))
                .body("isFirst", is(true))
                .body("isLast", is(true))
                .body("hasNext", is(false))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldBeAbleToGetProduct() {
        Product product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", "B100")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(Product.class);

        assertThat(product.code()).isEqualTo("B100");
        assertThat(product.name()).isEqualTo("The Self-Taught Computer Scientist");
        assertThat(product.description())
                .isEqualTo("It’s also a sequel to The Self-Taught Programmer. "
                        + "Both of these are excellent introductions to the discipline by an expert "
                        + "programmer and computer scientist. In this book, you’ll learn more about "
                        + "not only being a better programmer but also about how to ace programming "
                        + "job interviews. This book is perfect for those preparing to apply for jobs "
                        + "in the field, especially those who are moving from college to the workforce.");
        assertThat(product.price()).isEqualTo(new BigDecimal("18.52"));
    }

    @Test
    void shouldNotBeAbleToGetProductByCodeNotExist() {
        String code = "B10000";
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", is("Product with code " + code + " not found"));
    }
}
