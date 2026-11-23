package booking.test;

import booking.TestBase;
import booking.assertions.ResponseAssertions;
import booking.dto.BookingPatchRequest;
import booking.steps.BookingSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static booking.config.SchemaPaths.UPDATE_BOOKING_SCHEMA;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PartialUpdateBookingPositiveTest extends TestBase {
    BookingSteps bookingSteps = new BookingSteps();
    private String token;
    private int bookingId;

    @BeforeEach
    void beforeEach() {
        token = bookingSteps.getAuthToken();
        bookingId = bookingSteps.createBooking().getBookingid();
    }

    @Test
    void partialUpdateBooking() {
        BookingPatchRequest requestBody = BookingPatchRequest.builder()
                .firstname("Иван")
                .lastname("Добродубов")
                .build();

        Response response = bookingSteps.partialUpdateBooking(requestBody, token, bookingId);

        assertAll(
                () -> assertThat(response.getStatusCode()).isEqualTo(200),
                () -> ResponseAssertions.checkJsonSchema(response, UPDATE_BOOKING_SCHEMA),
                () -> assertThat(response.jsonPath().getString("firstname")).isEqualTo("Иван"),
                () -> assertThat(response.jsonPath().getString("lastname")).isEqualTo("Добродубов"));
    }
}
