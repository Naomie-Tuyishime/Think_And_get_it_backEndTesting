package tests.userProfile;

import org.testng.annotations.Test;

import static constants.StatusCode.CODE_200;
import static io.restassured.RestAssured.given;
import static routes.Routes.GET_SINGLE_USER;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;

public class SingleUserTest {
    @Test
     public void getSingleUSer (){
        given().spec(getRequestSpec())
                .get( GET_SINGLE_USER).
                then().
                spec(getResponseSpec()).
                statusCode(CODE_200.getCode());


     }
}

