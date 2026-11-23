package booking.test;

import booking.TestBase;
import booking.apiclient.ApiClient;
import booking.assertions.ResponseAssertions;
import booking.dto.CreateBookingRequest;
import booking.dto.CreateBookingResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static booking.config.SchemaPaths.BOOKING_SCHEMA;
import static booking.factory.BookingRequestFactory.buildCreateBooking;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CreateBookingPositiveTest extends TestBase {

    @Test
    void createBookingHappyPassTest() {

        ApiClient apiClient = new ApiClient();

        CreateBookingRequest request = buildCreateBooking();

        Response responseCreate = apiClient.createBooking(request);

        CreateBookingResponse response = responseCreate.as(CreateBookingResponse.class);

        assertAll(
                () -> assertThat(responseCreate.getStatusCode()).isEqualTo(200),
                () -> ResponseAssertions.checkJsonSchema(responseCreate, BOOKING_SCHEMA),
                () -> assertThat(response.getBookingid()).isPositive(),
                () -> assertThat(response.getBooking().getFirstname()).isEqualTo(request.getFirstname()),
                () -> assertThat(response.getBooking().getLastname()).isEqualTo(request.getLastname()),
                () -> assertThat(response.getBooking().getTotalprice()).isEqualTo(request.getTotalprice()),
                () -> assertThat(response.getBooking().isDepositpaid()).isEqualTo(request.isDepositpaid()),
                () -> assertThat(response.getBooking().getBookingdates().getCheckin())
                        .isEqualTo(request.getBookingdates().getCheckin()),
                () -> assertThat(response.getBooking().getBookingdates().getCheckout())
                        .isEqualTo(request.getBookingdates().getCheckout())
        );
    }
}