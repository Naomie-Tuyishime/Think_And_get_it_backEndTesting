package base;

import org.testng.annotations.BeforeClass;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static routes.Routes.PRODUCTS;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.FakerUtils.getDescription;
import static utils.FakerUtils.getFirstName;
import static utils.TokenManager.getToken;

public class BaseTest {

    protected String token;
    protected String dynamicCategoryId;

    @BeforeClass
    public void setupTestData() {
        token = getToken("login");

        Map<String, Object> categoryPayload = new HashMap<>();
        categoryPayload.put("name", getFirstName());
        categoryPayload.put("description", getDescription());

        dynamicCategoryId =
                given()
                        .spec(getRequestSpec())
                        .header("Authorization", "Bearer " + token)
                        .body(categoryPayload)
                        .when()
                        .post(PRODUCTS)
                        .then().spec(getResponseSpec())
                        .statusCode(201)
                        .extract()
                        .path("data.id");
    }
}