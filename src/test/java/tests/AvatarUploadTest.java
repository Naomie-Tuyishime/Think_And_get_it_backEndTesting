package tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static routes.Routes.USERS_AVAR_UPLOAD;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.TokenManager.getToken;

public class AvatarUploadTest {

    @Test
    public void uploadUserAvatar() {

        String token = getToken("login");

        File avatar = new File("src/test/resources/avatar.png");

        given()
                .spec(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.MULTIPART)
                .multiPart("avatar", avatar)
                .when()
                .post(USERS_AVAR_UPLOAD)
                .then()
                .spec(getResponseSpec())
                .statusCode(200);
    }
}