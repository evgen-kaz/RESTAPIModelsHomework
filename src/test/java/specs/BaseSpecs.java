package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import tests.TestBase;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class BaseSpecs extends TestBase {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .header("x-api-key", API_KEY)
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .basePath("/api");

    public static RequestSpecification requestCreateSpec = with()
            .filter(withCustomTemplates())
            .header("x-api-key", API_KEY)
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .basePath("/api");

    public static ResponseSpecification responseSpec(int expectedStatusCode) { 
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .log(STATUS)
                .log(BODY)
                .build();
    }
}
