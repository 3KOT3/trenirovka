package booking.config;

import org.aeonbits.owner.ConfigFactory;

public class Config {

    private static final ApiConfig config = ConfigFactory.create(ApiConfig.class);

    public static ApiConfig apiConfig() {
        return config;
    }
}