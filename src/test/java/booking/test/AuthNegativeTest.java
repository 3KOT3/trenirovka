package booking.test;

import booking.TestBase;
import booking.steps.BookingSteps;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class AuthNegativeTest extends TestBase {
    BookingSteps bookingSteps = new BookingSteps();

    @ParameterizedTest
    @MethodSource("invalidBodies")
    void authNegativeTest(String username, String password) {

        Response responseAuth = bookingSteps.auth(username, password);

        assertAll(
                () -> assertThat(responseAuth.jsonPath().getString("token")).isNullOrEmpty()
        );
    }

    static Stream<Arguments> invalidBodies() {
        return Stream.of(
                Arguments.of("admin", "password"),
                Arguments.of("petya", "password123"),
                Arguments.of("admin", ""),
                Arguments.of("", "password123"),
                Arguments.of(null, null),
                Arguments.of((String) null, (String) null)
        );
    }
}
