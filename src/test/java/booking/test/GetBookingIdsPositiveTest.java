package booking.test;

import booking.TestBase;
import booking.assertions.ResponseAssertions;
import booking.dto.BookingId;
import booking.dto.CreateBookingRequest;
import booking.steps.BookingSteps;
import booking.util.TestData;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static booking.config.SchemaPaths.GET_IDS_BOOKING_SCHEMA;
import static booking.factory.BookingRequestFactory.buildCreateBooking;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GetBookingIdsPositiveTest extends TestBase {
    BookingSteps bookingSteps = new BookingSteps();
    private String firstname = TestData.randomFirstName();
    private String lastname = TestData.randomLastName();
    private int quantity = 5;

    List<Integer> bookingIds = new ArrayList<>();

    @BeforeEach
    void beforeEach() {

        for (int i = 0; i < quantity; i++) {
            CreateBookingRequest request = buildCreateBooking().toBuilder()
                    .firstname(firstname)
                    .lastname(lastname)
                    .build();

            Integer bookingId = bookingSteps.createBooking(request).getBookingid();
            bookingIds.add(bookingId);
        }
    }

    @Test
    void getBookingByLastNameAndFirstName() {

        Response response = bookingSteps.getBookingIds(Map.of(
                "lastname", lastname,
                "firstname", firstname));

        List<BookingId> responseGet = response.as(new TypeRef<List<BookingId>>() {});

        assertAll(
                () -> assertThat(response.getStatusCode()).isEqualTo(200),
                () -> ResponseAssertions.checkJsonSchema(response, GET_IDS_BOOKING_SCHEMA),
                () -> assertThat(responseGet).hasSize(quantity)
                        .doesNotContainNull()
                        .doesNotHaveDuplicates(),
                () -> assertThat(responseGet.stream().map(BookingId::bookingid).toList())
                        .containsExactlyInAnyOrderElementsOf(bookingIds)
        );
    }
}
