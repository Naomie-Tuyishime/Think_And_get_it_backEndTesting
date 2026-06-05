package tests;

import constants.StatusCode;
import org.testng.annotations.Test;
import routes.Routes;

import static constants.StatusCode.CODE_200;
import static io.restassured.RestAssured.given;
import static routes.Routes.UsersAddress;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.TokenManager.getToken;

public class getUserAddress {
    @Test
    public void getUserAddressTest(){

        String token = getToken("login");
        given().
                spec(getRequestSpec()).header("Authorization", "Bearer " + token).
                when().
                get(UsersAddress).
                then().spec(getResponseSpec()).
                statusCode(CODE_200.getCode());
    }
}
