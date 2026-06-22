package tests.auth;

import constants.StatusCode;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static routes.Routes.USER_LOGIN;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;

public class LoginTest {
    @Test
    public void verifyLoginAPI() {


        File file = new File("src/test/resources/LoginCridentials.json");
        given()
                .spec(getRequestSpec())
                .body(file)
                .when()
                .post(USER_LOGIN)
                .then()
                .spec(getResponseSpec())
                .statusCode(StatusCode.CODE_200.getCode())
                .body("message", equalTo("Login successful"));
    }


}
