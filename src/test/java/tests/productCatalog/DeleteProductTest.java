package tests.productCatalog;

import constants.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static routes.Routes.PRODUCT;
import static routes.Routes.fakeProductId;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static tests.productCatalog.CreatingProduct.createProductTest;
import static utils.TokenManager.getToken;

public class DeleteProductTest {
    String token = getToken("login");
    String targetProductId = createProductTest();
    @Test
    public void deleteProductTest (){
        Response response =
                given()
                        .spec(getRequestSpec())
                        .pathParam("id", targetProductId)
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .delete(PRODUCT)
                        .then().spec(getResponseSpec())
                        .statusCode(StatusCode.CODE_200.getCode())
                        .extract()
                        .response();

        Assert.assertEquals(response.jsonPath().getString("message"), "Product deactivated");

        String deletedId = response.jsonPath().getString("data.id");

        Assert.assertEquals(deletedId, targetProductId);

        System.out.println("The API confirmed deletion of ID: " + deletedId);


    }

    @Test
    public void  deleteProductWithNonExistingId (){
      Response response =  given()
                .spec(getRequestSpec())
                .pathParam("id",  fakeProductId)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(PRODUCT)
                .then().spec(getResponseSpec())
                .statusCode(StatusCode.CODE_404.getCode())
                .extract()
                .response();
        Assert.assertEquals(response.jsonPath().getString("message"), "Record not found");


    }
    @Test
    public void verifyIfProductDeletedSuccessfully (){
        Response getResponse =
                given()
                        .spec(getRequestSpec())
                        .pathParam("id", targetProductId)
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get(PRODUCT)
                        .then().spec(getResponseSpec())
                        .statusCode(StatusCode.CODE_404.getCode())
                        .extract()
                        .response();
        Assert.assertEquals(getResponse.jsonPath().getString("message"), "Product not found");

    }
}
