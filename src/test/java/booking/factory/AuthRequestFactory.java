package booking.factory;

import booking.config.Config;
import booking.dto.AuthRequest;

public class AuthRequestFactory {

    public static AuthRequest buildAuthRequest() {
        return AuthRequest.builder()
                .username(Config.apiConfig().username())
                .password(Config.apiConfig().password())
                .build();
    }

    public static AuthRequest buildAuthRequest(String username, String password) {
        return AuthRequest.builder()
                .username(username)
                .password(password)
                .build();
    }
}
