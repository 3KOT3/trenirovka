package booking.test;

import booking.TestBase;
import booking.apiclient.ApiClient;
import booking.dto.CreateBookingRequest;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static booking.factory.BookingRequestFactory.buildCreateBooking;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateBookingNegativeTest extends TestBase {

    ApiClient apiClient = new ApiClient();

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidBody")
    void createBookingNegative(String caseName, CreateBookingRequest invalidRequest, int expectedStatusCode) {

        Response response = apiClient.createBooking(invalidRequest);

        assertThat(response.getStatusCode()).isEqualTo(expectedStatusCode);
    }

    private static Stream<Arguments> invalidBody() {
        return Stream.of(Arguments.of("Без firstname",
                        buildCreateBooking().toBuilder().firstname(null).build(), 500),
                Arguments.of("Без lastname",
                        buildCreateBooking().toBuilder().lastname(null).build(), 500),
                Arguments.of("Отрицательная цена",
                        buildCreateBooking().toBuilder().totalprice(-500).build(), 200),
                Arguments.of("Неверный формат даты",
                        buildCreateBooking().toBuilder()
                                .bookingdates(CreateBookingRequest.BookingDates.builder()
                                        .checkin("не-дата")
                                        .checkout("2026-11-10")
                                        .build()).build(), 200),
                Arguments.of("Выезд раньше заезда",
                        buildCreateBooking().toBuilder()
                                .bookingdates(CreateBookingRequest.BookingDates.builder()
                                        .checkin("2026-11-10")
                                        .checkout("2026-11-01")
                                        .build()).build(), 200),
                Arguments.of("Пустое body", new CreateBookingRequest(), 500)
        );
    }
}