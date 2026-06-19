package tests.productCatalog;

import constants.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static payload.ProductPayload.createProduct;
import static routes.Routes.PRODUCTS;
import static spec.SpecBuilder.getRequestSpec;
import static utils.TokenManager.getToken;


public class CreatingProduct {

    @Test
    public void createProductTest() {

        String token = getToken("login");

        var payload = createProduct("ddb77290-34ef-4e20-a19b-febca2c5c9d1");
        Response response =
                given()
                        .spec(getRequestSpec())
                        .header("Authorization", "Bearer " + token)
                        .body(payload)
                        .when()
                        .post(PRODUCTS)
                        .then()
                        .statusCode(StatusCode.CODE_201.getCode())
                        .extract()
                        .response();

        Assert.assertNotNull(response.jsonPath().get("data.id"));

        Assert.assertEquals(
                response.jsonPath().get("data.name"),
                payload.get("name")
        );

        Assert.assertEquals(
                response.jsonPath().get("data.price"),
                payload.get("price")
        );

        Assert.assertTrue(
                response.jsonPath().getList("data.variants").size() > 0
        );


        System.out.println("Created Product ID: " + response.jsonPath().get("data.id"));
    }
}