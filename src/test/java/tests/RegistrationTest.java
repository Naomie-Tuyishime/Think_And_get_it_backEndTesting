package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static constants.StatusCode.CODE_201;
import static io.restassured.RestAssured.given;
import static routes.Routes.USER_REGISTER;
import static routes.Routes.VERIFY_EMAIL;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.TokenManager.saveTokens;
import static utils.TokenManager.getToken;

public class RegistrationTest {

    File file = new File("src/test/resources/registrationCridentials.json");

    @Test
    public void UserRegistrationTest() {

        Response response =
                given()
                        .spec(getRequestSpec())
                        .body(file)
                        .when()
                        .post(USER_REGISTER)
                        .then()
                        .spec(getResponseSpec())
                        .extract()
                        .response();


        String token = response.path("data.token");
        String refreshToken = response.path("data.refreshToken");

        System.out.println("FULL RESPONSE: " + response.asString());

        saveTokens("register", token, refreshToken);
    }

    @Test
    public void verifyEmailAddress() {
        String token = getToken("register");
        System.out.println("TOKEN = " + token);
        given()
                .spec(getRequestSpec())
                .pathParam("token", token)
                .when()
                .get(VERIFY_EMAIL)
                .then()
                .spec(getResponseSpec())
              .statusCode(CODE_201.getCode());

    }
}