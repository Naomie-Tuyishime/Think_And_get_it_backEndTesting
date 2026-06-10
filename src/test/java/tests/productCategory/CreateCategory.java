package tests.productCategory;

import constants.StatusCode;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static constants.StatusCode.CODE_201;
import static io.restassured.RestAssured.given;
import static routes.Routes.CATEGORIES;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.FakerUtils.*;
import static utils.TokenManager.getToken;

public class CreateCategory {
    @Test

    public void CreateCategoryTest() {
        String token = getToken("login");

        Map<String, Object> payload = new HashMap<>();
        payload.put("name", getFirstName());
        payload.put("description", getDescription());

        String categoryId =
                given()
                        .spec(getRequestSpec())
                        .header("Authorization", "Bearer " + token)
                        .body(payload)
                        .when()
                        .post(CATEGORIES)
                        .then()
                        .statusCode(CODE_201.getCode())
                        .extract()
                        .path("data.id");

        System.out.println("Created category ID: " + categoryId);
    }
    @Test
    public void CreateCategory_MissingName() {
        String token = getToken("login");

        Map<String, Object> payload = new HashMap<>();
        payload.put("description", getDescription());

        given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .when()
                .post(CATEGORIES)
                .then()
                .statusCode(StatusCode.CODE_500.getCode());
    }
    @Test
    public void createCategoryWithoutToken() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("name", getFirstName());
        payload.put("description", getDescription());

        given()
                .spec(getRequestSpec())
                .body(payload)
                .when()
                .post(CATEGORIES)
                .then()
                .spec(getResponseSpec())
                .statusCode(StatusCode.CODE_401.getCode());
    }
    @Test
    public void createCategoryWithoutName() {

        String token = getToken("login");

        Map<String, Object> payload = new HashMap<>();
        payload.put("description", getDescription());

        given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .when()
                .post(CATEGORIES)
                .then()
                .spec(getResponseSpec())
                .statusCode(StatusCode.CODE_500.getCode());
    }
}
