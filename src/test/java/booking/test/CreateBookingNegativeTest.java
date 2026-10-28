package booking.test;

import booking.TestBase;
import booking.dto.CreateBookingRequest;
import booking.dto.CreateBookingResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static booking.config.Config.BOOKING_URL;
import static dk.brics.automaton.StringUnionOperations.build;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateBookingNegativeTest extends TestBase {

    @ParameterizedTest
    @Tag("negative")
    @DisplayName("Бронирование отеля с невалдиным телом запроса")
    @MethodSource("invalidBody")
    public void createBookingNegativeTest(CreateBookingRequest invalidBody, int status) {

        given()
                .contentType(ContentType.JSON)
                .body(invalidBody)
                .post(BOOKING_URL + "/booking")
                .then()
                .statusCode(status);

    }

    private static CreateBookingRequest buildCreateBooking() {
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

    static Stream<Arguments> invalidBody() {
        return Stream.of(
                Arguments.of(mutateBody(body -> body.setFirstname(null)), 500),
                Arguments.of(mutateBody(body -> body.setLastname(null)), 500),
                Arguments.of(mutateBody(body -> body.setTotalprice(-500)), 200)
        );
    }

    private static CreateBookingRequest mutateBody(Consumer<CreateBookingRequest> deleter) {
        CreateBookingRequest body = buildCreateBooking();
        deleter.accept(body);
        return body;
    }

}


//class RequestDtoTest {
//
//    // Источник данных поставляет уже модифицированные DTO
//    static Stream<MyRequest> invalidRequests() {
//        return Stream.of(
//                new MyRequest(null, "test@mail.com", "pass123"),  // Без username
//                new MyRequest("user123", null, "pass123"),        // Без email
//                new MyRequest("user123", "test@mail.com", null)   // Без password
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("invalidRequests")
//    void testInvalidBodies(MyRequest invalidBody) {
//        System.out.println("Тестируем тело: " + invalidBody);
//
//        // Отправка invalidBody в запрос
//        // given().body(invalidBody).post("/endpoint").then().statusCode(400);
//    }
//}
