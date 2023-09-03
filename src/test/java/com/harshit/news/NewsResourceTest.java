package com.harshit.news;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
@QuarkusTestResource(WiremockNews.class)
class NewsResourceTest {

    @Test
    void getNewsById() {
        given()
                .baseUri("http://localhost:8089")
                .when()
                .pathParam("id",2)
                .get("/news/{id}")
                .then()
                .body("newsId", equalTo("2"))
                .body("title", equalTo("abcd"));
    }

    @Test
    void createNews() {
        String requestBody = "{\"title\":\"wiremock test\",\"content\":\"qwertyuiop\",\"author\":\"quarkus\",\"category\":\"testing\"}";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/news/create")
                .then()
                .contentType(ContentType.JSON)
                .body("title", equalTo("wiremock test"));
    }
}