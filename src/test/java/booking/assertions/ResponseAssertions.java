package booking.assertions;

import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.BDDAssertions.then;

public class ResponseAssertions {

    public static void checkJsonSchema(Response response, String schemasPath) {
        response.then()
                .body(matchesJsonSchemaInClasspath(schemasPath));
    }
}

