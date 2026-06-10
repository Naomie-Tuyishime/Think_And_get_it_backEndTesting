package tests.productCategory;

import constants.StatusCode;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static routes.Routes.SINGLECATEGORY;
import static routes.Routes.SINGLEPRODUCTID;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.dataLoader.getProductSlug;


public class GetSingleCategory {
    @Test
    public void GetSingleCategory (){

        given().spec(getRequestSpec()).pathParam("slug",getProductSlug()).get( SINGLECATEGORY).
                then().spec(getResponseSpec()).statusCode(StatusCode.CODE_200.getCode());


    }
    @Test
    public void getSingleCategory_InvalidSlug() {

        given()
                .spec(getRequestSpec())
                .pathParam("slug", "invalid-slug-123")
                .when()
                .get(SINGLECATEGORY)
                .then()
                .spec(getResponseSpec())
                .statusCode(StatusCode.CODE_404.getCode());
    }

}
