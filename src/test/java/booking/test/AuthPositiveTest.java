package booking.test;

import booking.TestBase;
import booking.dto.AuthRequest;
import booking.dto.AuthResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static booking.config.Config.BOOKING_URL;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthPositiveTest extends TestBase {


    @Test
    @DisplayName("Успешная авторизация с валидными данными")
    @Tag("positive")
    public void authTest() {

        String user = "admin";
        String password = "password123";

        AuthResponse response = given()                                   // дало
                .contentType(ContentType.JSON)
                .body(new AuthRequest(user, password))
                .post(BOOKING_URL + "/auth")
                .then()                                                  // проверки
                .statusCode(200)
                .extract().as(AuthResponse.class);

        assertThat(response.getToken()).isNotNull();                     // assertThat - убедиться что
    }
}
