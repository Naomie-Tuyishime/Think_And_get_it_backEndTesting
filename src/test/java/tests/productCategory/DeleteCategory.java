package tests.productCategory;

import constants.StatusCode;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static constants.StatusCode.CODE_200;
import static io.restassured.RestAssured.given;
import static routes.Routes.CATEGORIES;
import static routes.Routes.SINGLEPRODUCTID;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.FakerUtils.getDescription;
import static utils.FakerUtils.getFirstName;
import static utils.TokenManager.getToken;
import static utils.dataLoader.getProductId;
import static utils.dataLoader.getProductSlug;

public class DeleteCategory {
    @Test
    public void deleteCategory() {
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
                        .extract()
                        .path("data.id");


        given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(CATEGORIES + "/" + categoryId)
                .then()
                .statusCode(CODE_200.getCode());



}}