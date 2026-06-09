package tests.addressManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;
import static routes.Routes.UsersAddress;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.TokenManager.getToken;

public class GetUserAddress {
    @Test
    public void GetUserAddressTest(){

        String token = getToken("login");
      Response response =  given().
                spec(getRequestSpec()).header("Authorization", "Bearer " + token).
                when().
                get(UsersAddress).
                then().spec(getResponseSpec()).
              extract().response();

      response.then().
              body("success",
                      equalTo(true),("message"),
              equalTo("Success"));





    }
}
