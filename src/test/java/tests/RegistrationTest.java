package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.FakerUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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

    @Test(priority = 1)
    public void userRegistrationTest() {

      Map<String, Object>  payload = new HashMap<>();

        payload.put("email", FakerUtils.getEmail());
        payload.put("password", FakerUtils.getPassword());
        payload.put("firstName", FakerUtils.getFirstName());
        payload.put("lastName", FakerUtils.getLastName());
        payload.put("phone", FakerUtils.getPhone());

        Response response =
                given()
                        .spec(getRequestSpec())
                        .body(payload)
                        .when()
                        .post(USER_REGISTER)
                        .then()
                        .spec(getResponseSpec())
                        .statusCode(CODE_201.getCode())
                        .extract()
                        .response();

        String token = response.path("data.token");
        String refreshToken = response.path("data.refreshToken");

        saveTokens("register", token, refreshToken);

        System.out.println("REGISTER RESPONSE: " + response.asPrettyString());
    }
}