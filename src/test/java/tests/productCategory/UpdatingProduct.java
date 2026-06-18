package tests.productCategory;

import constants.StatusCode;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static constants.StatusCode.CODE_200;
import static io.restassured.RestAssured.given;
import static routes.Routes.SINGLEPRODUCTID;

import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.FakerUtils.getFirstName;
import static utils.TokenManager.getToken;
import static utils.dataLoader.getProductId;
import static utils.dataLoader.getProductSlug;

public class UpdatingProduct {
    @Test
    public void UpdateProductTest (){
        String token = getToken("login");
        Map<Object, String > payload = new HashMap<>();
        payload.put("name",getFirstName());

        given().
                spec(getRequestSpec()).pathParam("id",getProductId()).
                header("Authorization", "Bearer " + token).
                body(payload).
                when().put(SINGLEPRODUCTID ).
                then().spec(getResponseSpec()).statusCode(CODE_200.getCode());


    }
    @Test
    public void updateProductWithInvalidId() {

        String token = getToken("login");

        Map<Object, String> payload = new HashMap<>();
        payload.put("name", getFirstName());

        given()
                .spec(getRequestSpec())
                .pathParam("id", "invalid-id-123")
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .when()
                .put(SINGLEPRODUCTID)
                .then()
                .spec(getResponseSpec())
                .statusCode(StatusCode.CODE_404.getCode());
    }
    @Test
    public void updateProductWithoutToken() {

        Map<Object, String> payload = new HashMap<>();
        payload.put("name", getFirstName());

        given()
                .spec(getRequestSpec())
                .pathParam("id", getProductId())
                .body(payload)
                .when()
                .put(SINGLEPRODUCTID)
                .then()
                .spec(getResponseSpec())
                .statusCode(StatusCode.CODE_401.getCode());
    }
}
