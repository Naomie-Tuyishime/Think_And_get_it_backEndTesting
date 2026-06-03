package spec;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ConfigLoader;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.lessThan;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigLoader.getBaseUrl())
                .setContentType(JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .expectContentType(JSON).
                log(LogDetail.ALL)
                .build();
    }


}