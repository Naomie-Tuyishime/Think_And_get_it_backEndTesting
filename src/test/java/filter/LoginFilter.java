package filter;

import constants.StatusCode;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import utils.TokenManager;

import static constants.StatusCode.CODE_200;

public class LoginFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);

        if (requestSpec.getURI().contains("/auth/login") && response.getStatusCode() == CODE_200.getCode()) {
            String token = response.jsonPath().getString("data.token");
            String refresh = response.jsonPath().getString("data.refreshToken");

            if (token != null && !token.isBlank() && refresh != null && !refresh.isBlank()) {
                TokenManager.saveTokens("login", token, refresh);
            }
        }
        return response;
    }
}
