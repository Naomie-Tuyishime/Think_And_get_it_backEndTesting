package tests;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static constants.StatusCode.CODE_200;
import static constants.StatusCode.CODE_201;
import static io.restassured.RestAssured.given;
import static routes.Routes.UsersAddress;
import static spec.SpecBuilder.getRequestSpec;
import static spec.SpecBuilder.getResponseSpec;
import static utils.FakerUtils.*;
import static utils.TokenManager.getToken;

public class AddUserAddress {
    @Test
    public void AddUserAddressTest (){
        String token = getToken("login");
        Map<String, Object> payload = new HashMap<>();

        payload.put("label", "Home");
        payload.put("firstName", getFirstName());
        payload.put("lastName", getLastName());
        payload.put("phone", getPhone());
        payload.put("street", getStreet());
        payload.put("city", getCity());
        payload.put("state", getState());
        payload.put("country",getCountry());
        payload.put("postalCode", getPostalCode());
        payload.put("isDefault", true);

        given().
                spec(getRequestSpec()).header("Authorization", "Bearer "+ token).
        body(payload).
                when().
                post(UsersAddress).
                then().spec(getResponseSpec()).
                statusCode(CODE_201.getCode());
    }

    }


