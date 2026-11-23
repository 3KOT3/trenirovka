package booking.test;

import booking.TestBase;
import booking.steps.BookingSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DeleteBookingPositiveTest extends TestBase {
    BookingSteps bookingSteps = new BookingSteps();

    private String token;
    private int bookingId;

    @BeforeEach
    void beforeEach() {
        token = bookingSteps.getAuthToken();
        bookingId = bookingSteps.createBooking().getBookingid();
    }

    @Test
    void deleteBooking() {
        Response response = bookingSteps.deleteBooking(token, bookingId);

        assertAll(
                () -> assertThat(response.getStatusCode()).isEqualTo(201),
                () -> assertThat(bookingSteps.getBooking(bookingId).getStatusCode()).isEqualTo(404)
        );
    }
}
