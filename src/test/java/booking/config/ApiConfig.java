package booking.config;

import com.fasterxml.jackson.annotation.JsonMerge;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config_property/booking.properties",
})
public interface ApiConfig extends Config {

    String username();

    String password();

    String bookingUrl();
}
