package booking.test;

import booking.TestBase;
import booking.dto.AuthRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static booking.config.Config.BOOKING_URL;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthNegativeTest extends TestBase {


    @Tag("negative")
    @DisplayName("Не успешная авторизаия с невалидными данными")
    @ParameterizedTest
    @MethodSource("negativeAuthData")
    public void authNegativeTest(String user, String password) {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest(user, password))
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200)
                .extract().response();

        assertThat(response.jsonPath().getMap("")).doesNotContainKey("token");
    }

    static Stream<Arguments> negativeAuthData() {
        return Stream.of(
                Arguments.of("user", "password"),
                Arguments.of("mister", "password123"),
                Arguments.of("user", ""),
                Arguments.of("", "password123"),
                Arguments.of("", ""));
    }

    @Test
    @DisplayName("Не успешная авторизация без тела запроса")
    @Tag("negative")
    public void authNotBodyTest() {

        Response response = given()
                .contentType(ContentType.JSON)
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200)
                .extract().response();

        assertThat(response.jsonPath().getMap("")).doesNotContainKey("token");
    }
}
