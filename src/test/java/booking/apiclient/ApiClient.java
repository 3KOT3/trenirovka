package booking.apiclient;

import booking.config.Config;
import booking.dto.AuthRequest;
import booking.dto.BookingPatchRequest;
import booking.dto.CreateBookingRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static booking.config.Endpoints.AUTH;
import static booking.config.Endpoints.BOOKING;
import static booking.util.RestAssuredRequestSpec.requestSpec;
import static io.restassured.RestAssured.given;

public class ApiClient {

    public Response auth(AuthRequest request) {
        return given(requestSpec)
                .baseUri(Config.apiConfig().bookingUrl())
                .body(request)
                .post(AUTH)
                .then().extract().response();
    }

    public Response authWithoutBody() {
        return given(requestSpec)
                .baseUri(Config.apiConfig().bookingUrl())
                .contentType(ContentType.JSON)
                .post(AUTH)
                .then().extract().response();
    }

    public Response createBooking(CreateBookingRequest request) {
        return given(requestSpec)
                .baseUri(Config.apiConfig().bookingUrl())
                .body(request)
                .post(BOOKING)
                .then()
                .extract().response();
    }

    public Response updateBooking(CreateBookingRequest request, String token, int bookingId) {
        return given(requestSpec)
                .cookie("token", token)
                .baseUri(Config.apiConfig().bookingUrl())
                .pathParam("bookingId", bookingId)
                .body(request)
                .put(BOOKING + "/{bookingId}")
                .then().extract().response();
    }

    public Response partialUpdateBooking(BookingPatchRequest request, String token, int bookingId) {
        return given(requestSpec)
                .cookie("token", token)
                .baseUri(Config.apiConfig().bookingUrl())
                .pathParam("bookingId", bookingId)
                .body(request)
                .patch(BOOKING + "/{bookingId}")
                .then().extract().response();
    }

    public Response getBooking(int bookindId) {
        return given(requestSpec)
                .baseUri(Config.apiConfig().bookingUrl())
                .pathParam("bookingId", bookindId)
                .get(BOOKING + "/{bookingId}")
                .then().extract().response();
    }

    public Response getBookingIds(Map<String, Object> queryParams) {
        return given(requestSpec)
                .queryParams(queryParams)
                .baseUri(Config.apiConfig().bookingUrl())
                .get(BOOKING)
                .then().extract().response();
    }

    public Response deleteBooking(String token, int bookingId) {
        return given(requestSpec)
                .cookie("token", token)
                .contentType(ContentType.JSON)
                .baseUri(Config.apiConfig().bookingUrl())
                .pathParam("bookingId", bookingId)
                .delete(BOOKING + "/{bookingId}")
                .then().extract().response();
    }
}
