package tests.productCatalog;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static routes.Routes.PRODUCTS;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;

public class GetAllProducts {

    @Test
    public void getAllProducts() {

        Response response = given()
                .spec(getRequestSpec())
                .queryParam("page", 1)
                .queryParam("limit", 20)
                .when()
                .get(PRODUCTS)
                .then()
                .spec(getResponseSpec())
                .extract()
                .response();


        int size = response.jsonPath().getList("data").size();
        Assert.assertTrue(size > 0, "Products should not be empty");
        Assert.assertTrue(size <= 20, "Products should respect limit=20");

        long time = response.time();
        Assert.assertTrue(time < 5000, "Response too slow: " + time + " ms");
    }
}
