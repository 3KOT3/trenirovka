package booking.factory;

import booking.dto.CreateBookingRequest;

import static booking.util.TestData.*;

public class BookingRequestFactory {

    public static CreateBookingRequest buildCreateBooking() {
        return CreateBookingRequest.builder()
                .lastname(randomLastName())
                .firstname(randomFirstName())
                .totalprice(randomTotalPrice())
                .depositpaid(true)
                .bookingdates(CreateBookingRequest.BookingDates.builder()
                        .checkin("2026-11-01")
                        .checkout("2026-11-10")
                        .build())
                .build();
    }
}
