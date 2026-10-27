package booking.test;

import booking.dto.CreateBookingRequest;
import booking.dto.CreateBookingResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import webshop.TestBase;

import static booking.config.Config.BOOKING_URL;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateBookingPositiveTest extends TestBase {

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
}
