package booking.test;

import booking.TestBase;
import booking.assertions.ResponseAssertions;
import booking.dto.CreateBookingRequest;
import booking.steps.BookingSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static booking.config.SchemaPaths.UPDATE_BOOKING_SCHEMA;
import static booking.factory.BookingRequestFactory.buildCreateBooking;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UpdateBookingPositiveTest extends TestBase {

    private final BookingSteps bookingSteps = new BookingSteps();
    private String token;
    private int bookingId;

    @BeforeEach
    void beforeEach() {
        token = bookingSteps.getAuthToken();
        bookingId = bookingSteps.createBooking().getBookingid();
    }

    @Test
    void updateBookingHappyPassTest() {
        CreateBookingRequest request = buildCreateBooking();
        Response response = bookingSteps.updateBooking(request, token, bookingId);

        assertAll(
                () -> assertThat(response.getStatusCode()).isEqualTo(200),
                () -> ResponseAssertions.checkJsonSchema(response, UPDATE_BOOKING_SCHEMA),
                () -> assertThat(response.jsonPath().getString("firstname"))
                        .isEqualTo(request.getFirstname()),
                () -> assertThat(response.jsonPath().getString("lastname"))
                        .isEqualTo(request.getLastname()),
                () -> assertThat(response.jsonPath().getInt("totalprice"))
                        .isEqualTo(request.getTotalprice()),
                () -> assertThat(response.jsonPath().getBoolean("depositpaid"))
                        .isEqualTo(request.isDepositpaid()),
                () -> assertThat(response.jsonPath().getString("bookingdates.checkin"))
                        .isEqualTo(request.getBookingdates().getCheckin()),
                () -> assertThat(response.jsonPath().getString("bookingdates.checkout"))
                        .isEqualTo(request.getBookingdates().getCheckout())
        );
    }
}