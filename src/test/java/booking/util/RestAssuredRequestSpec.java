package booking.util;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static booking.util.CustomAllureListener.withCustomTemplates;

public class RestAssuredRequestSpec {

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .addFilter(withCustomTemplates())             // Allure
            .addFilter(new RequestLoggingFilter())        // Логи запроса в консоль
            .addFilter(new ResponseLoggingFilter())       // Логи ответа в консоль
            .setContentType(ContentType.JSON)
            .build();

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .build();

    public static void setUp() {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }
}