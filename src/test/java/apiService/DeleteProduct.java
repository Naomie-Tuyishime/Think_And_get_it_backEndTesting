package apiService;

import io.restassured.response.Response;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static routes.Routes.PRODUCTS;
import static routes.Routes.PRODUCT;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;

public class DeleteProduct {

    public static Response deleteProductRequest(String productId, String token) {
        return given()
                .spec(getRequestSpec())
                .pathParam("id", productId)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(PRODUCT)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }


    public static Response getProductRequest(String productId, String token) {
        return given()
                .spec(getRequestSpec())
                .pathParam("id", productId)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(PRODUCT)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }
}