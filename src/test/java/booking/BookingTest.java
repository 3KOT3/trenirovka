package booking;

import booking.dto.AuthRequest;
import booking.dto.AuthResponse;
import booking.dto.CreateBookingRequest;
import booking.dto.CreateBookingResponse;
import groovyjarjarantlr4.v4.runtime.atn.LexerAction;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.json.Json;

import java.util.stream.Stream;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class BookingTest {
    private final static String BOOKING_URL = "https://restful-booker.herokuapp.com";

    @Test
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


    @Test
    @Tag("positive")
    public void createBookingTest() {

        CreateBookingResponse response = given()
                .contentType(ContentType.JSON)
                .body(buidCreateBooking())
                .post(BOOKING_URL + "/booking")
                .then()
                .statusCode(200)
                .extract().as(CreateBookingResponse.class);

        assertThat(response.getBooking()).isNotNull();
        assertThat(response.getBooking().getTotalprice()).isEqualTo(1111111);
        assertThat(response.getBooking().getBookingdates().getCheckin()).isEqualTo("2018-01-01");
        assertThat(response.getBooking().getDepositpaid()).isTrue();
    }

    private static CreateBookingRequest buidCreateBooking() {
        return CreateBookingRequest.builder()
                .lastname("Jon")
                .firstname("Sina")
                .totalprice(1111111)
                .depositpaid(true)
                .bookingdates(CreateBookingRequest.BookingDates.builder()
                        .checkin("2018-01-01")
                        .checkout("2019-01-01")
                        .build())
                .additionalneeds("cake").build();
    }

    @Tag("negative")
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
