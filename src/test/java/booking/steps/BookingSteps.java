package booking.steps;

import booking.apiclient.ApiClient;
import booking.dto.BookingPatchRequest;
import booking.dto.CreateBookingRequest;
import booking.dto.CreateBookingResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static booking.factory.AuthRequestFactory.buildAuthRequest;
import static booking.factory.BookingRequestFactory.buildCreateBooking;

public class BookingSteps {
    private final ApiClient apiClient = new ApiClient();


    @Step("Получить токен авторизации")
    public String getAuthToken() {
        Response responseAuth = apiClient.auth(buildAuthRequest());
        return responseAuth.jsonPath().getString("token");
    }

    @Step("Авторизоваться")
    public Response auth() {
        return apiClient.auth(buildAuthRequest());
    }

    @Step("Авторизоваться с параметрами: {username}, {password}")
    public Response auth(String username, String password) {
        if (username == null && password == null) {
            return apiClient.authWithoutBody();   // не вызываем .body(...)
        }
        return apiClient.auth(buildAuthRequest(username, password));
    }

    @Step("Создать бронирование отеля")
    public CreateBookingResponse createBooking() {
        Response responseCreate = apiClient.createBooking(buildCreateBooking());
        return responseCreate.as(CreateBookingResponse.class);
    }

    @Step("Создать бронирование отеля с заданными данными")
    public CreateBookingResponse createBooking(CreateBookingRequest request) {
        Response responseCreate = apiClient.createBooking(request);
        return responseCreate.as(CreateBookingResponse.class);
    }

    @Step("Обновить полностью запись о бронировании отеля")
    public Response updateBooking(CreateBookingRequest updateRequest, String token, int bookingId) {
        return apiClient.updateBooking(updateRequest, token, bookingId);
    }

    @Step("Частично обновить запись о бронировании отеля")
    public Response partialUpdateBooking(BookingPatchRequest updateRequest, String token, int bookingId) {
        return apiClient.partialUpdateBooking(updateRequest, token, bookingId);
    }

    @Step("Получить информацию о бронировании отеля")
    public Response getBooking(int bookingId) {
        return apiClient.getBooking(bookingId);
    }

    @Step("Получить информацию о бронировании отеля c query параметрами")
    public Response getBookingIds(Map<String, Object> queryParams) {
        return apiClient.getBookingIds(queryParams);
    }

    @Step("Удалить бронирование отеля")
    public Response deleteBooking(String token, int bookingId) {
        return apiClient.deleteBooking(token, bookingId);
    }
}

