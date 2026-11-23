package booking;

import booking.util.RestAssuredRequestSpec;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {

    @BeforeAll
    static void setUp() {
        RestAssuredRequestSpec.setUp();
    }
}
