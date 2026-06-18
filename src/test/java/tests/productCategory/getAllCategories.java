package tests.productCategory;

import constants.StatusCode;
import org.testng.annotations.Test;

import static constants.StatusCode.CODE_200;
import static io.restassured.RestAssured.given;
import static routes.Routes.CATEGORIES;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;

public class getAllCategories {
    @Test
    public void getAllCategoriesTest(){
        given().spec(getRequestSpec())
                .when().get(CATEGORIES).then().spec(getResponseSpec()).
                statusCode(CODE_200.getCode());

    }

}
