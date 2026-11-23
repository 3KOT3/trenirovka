package booking.test;

import booking.TestBase;
import booking.apiclient.ApiClient;
import booking.assertions.ResponseAssertions;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static booking.config.SchemaPaths.AUTH_SCHEMA;
import static booking.factory.AuthRequestFactory.buildAuthRequest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class AuthPositiveTest extends TestBase {
    ApiClient apiClient = new ApiClient();

    @Test
    void authHappyPassTest() {

        Response responseAuth = apiClient.auth(buildAuthRequest());

        assertAll(
                () -> assertThat(responseAuth.getStatusCode()).isEqualTo(200),
                () -> assertThat(responseAuth.jsonPath().getString("token")).isNotBlank(),
                () -> ResponseAssertions.checkJsonSchema(responseAuth, AUTH_SCHEMA)
        );
    }
}
