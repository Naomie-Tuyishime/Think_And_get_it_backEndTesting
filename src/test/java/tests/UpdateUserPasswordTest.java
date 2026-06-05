package tests;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

import static routes.Routes.USER_CHANGE_PASSWORD;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.TokenManager.getToken;

public class UpdateUserPasswordTest {

    @Test
    public void updatePasswordTest() {

        String token = getToken("login");

        Map<String, Object> payload = new HashMap<>();

        payload.put("currentPassword", "McyPass@123");
        payload.put("newPassword", "McyPass@123st");

        given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .body(payload)

                .when()
                .put(USER_CHANGE_PASSWORD)

                .then()
                .spec(getResponseSpec())
                .statusCode(200);
    }
}