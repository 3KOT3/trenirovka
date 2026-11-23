package booking.test;

import booking.TestBase;
import booking.assertions.ResponseAssertions;
import booking.dto.CreateBookingResponse;
import booking.steps.BookingSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static booking.config.SchemaPaths.UPDATE_BOOKING_SCHEMA;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GetBookingPositiveTest extends TestBase {
    BookingSteps bookingSteps = new BookingSteps();

    private int bookingId;

    @BeforeEach
    void beforeEach() {
        bookingId = bookingSteps.createBooking().getBookingid();
    }

    @Test
    void getBooking() {
        Response response = bookingSteps.getBooking(bookingId);

        assertAll(
                () -> assertThat(response.getStatusCode()).isEqualTo(200),
                () -> ResponseAssertions.checkJsonSchema(response, UPDATE_BOOKING_SCHEMA));
    }
}
