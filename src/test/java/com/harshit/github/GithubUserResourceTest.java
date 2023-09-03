package com.harshit.github;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
@QuarkusTestResource(WiremockGithub.class)
class GithubUserResourceTest {

    //@Test
    void getUserByName() {
        RestAssured.given()
                .when()
                .pathParam("name", "garg1503")
                .get("/gitUser/{name}")
                .then()
                .body("login", equalTo("garg1503"))
                .body("name", equalTo("Harshit Garg"));
    }

    @Test
    void getUserByNameWithWiremock() {
        Response response = RestAssured.given()
                .baseUri("http://localhost:8089")
                .when()
                .pathParam("name", "garg")
                .get("/gitUser/{name}");

        System.out.println(response.getBody().asString());
                response.then()
                .body("login", equalTo("garg"))
                .body("name", equalTo("Harsh"));
    }
}