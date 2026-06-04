package tests;

import org.testng.annotations.Test;
import utils.FakerUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static routes.Routes.USER_PROFILE;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.TokenManager.getToken;

public class UpdateUserProfile {

    @Test
    public void userProfileTest() {


        String token = getToken("login");

        Map<String, Object> payload = new HashMap<>();

        payload.put("firstName", FakerUtils.getFirstName());
        payload.put("lastName", FakerUtils.getLastName());
        payload.put("phone", FakerUtils.getPhone());

        given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .body(payload)

                .when()
                .put(USER_PROFILE)
                .then()
                .spec(getResponseSpec())
                .statusCode(200);
    }
}