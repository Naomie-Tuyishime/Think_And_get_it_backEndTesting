package tests.productCatalog;

import constants.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static payload.ProductPayload.updateProduct;
import static routes.Routes.*;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.TokenManager.getToken;
import static utils.dataLoader.getProductId;

public class UpdateProduct {
    @Test
    public void updateProductTest (){
        String token = getToken("login");
        var payload = updateProduct(PRODUCT_ID);
        Response response =
                given()
                        .spec(getRequestSpec())
                        .pathParam("id", SINGLEPRODUCT)
                        .header("Authorization", "Bearer " + token)
                        .body(payload)
                        .when()
                        .put(PRODUCT)
                        .then().spec(getResponseSpec())
                        .statusCode(StatusCode.CODE_200.getCode())
                        .extract()
                        .response();

        Assert.assertNotNull(response.jsonPath().get("data.id"));
        System.out.println(" Updated product id " + response.jsonPath().get("data.id"));

    }
    @Test
    public void updateProductWithNegativePriceTest() {
        String token = getToken("login");
        var badPayload = updateProduct(SINGLEPRODUCT);

        badPayload.put("price", NEGATIVENUMBERS);
        badPayload.put("flashSalePrice",  NEGATIVENUMBERS);

        given()
                .spec(getRequestSpec())
                .pathParam("id", SINGLEPRODUCT)
                .header("Authorization", "Bearer " + token)
                .body(badPayload)
                .when()
                .put(PRODUCT)
                .then().spec(getResponseSpec())
                .statusCode(StatusCode.CODE_400.getCode());
    }
    @Test
    public void updateProductWithWrongHttpMethodTest() {
        String token = getToken("login");
        var payload = updateProduct(SINGLEPRODUCT);

        given()
                .spec(getRequestSpec())
                .pathParam("id", SINGLEPRODUCT)
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .when()

                .post(PRODUCT )
                .then()
                .statusCode(StatusCode.CODE_404.getCode());
    }

}
