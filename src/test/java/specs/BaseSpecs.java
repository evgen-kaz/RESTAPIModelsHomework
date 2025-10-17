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
            .filter(withCustomTemplates()) //вызов метода из класса CustomAllureListener
            .header("x-api-key", API_KEY)
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .basePath("/api");

    public static RequestSpecification requestCreateSpec = with()
            .filter(withCustomTemplates()) //вызов метода из класса CustomAllureListener
            .header("x-api-key", API_KEY)
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .basePath("/api");

    public static ResponseSpecification responseSpec(int expectedStatusCode) { //вернется объект ResponseSpecification
        return new ResponseSpecBuilder() //ResponseSpecBuilder - спец объект для создания объекта
                //return должен быть перед всем выражением (цепочка методов), которое возвращается
                .expectStatusCode(expectedStatusCode) //передаем динамически ОР и именно число кода
                .log(STATUS)
                .log(BODY)
                .build(); //завершает построение объекта и возвращает результат. После return его и подвешивают
    }
}
