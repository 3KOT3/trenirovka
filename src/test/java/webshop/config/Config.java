package webshop.config;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    public static final String WEBSHOP_HOST = "https://demowebshop.tricentis.com";
    public static final String WEBSHOP_LOGIN_HOST = WEBSHOP_HOST + "/login";

    public static ChromeOptions getSelenoidChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", config.browserVersion());

        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("name", "useToastTest");
        selenoidOptions.put("sessionTimeout", "15m");
        selenoidOptions.put("env", List.of("TZ=UTC"));
        selenoidOptions.put("labels", Map.of("manual", "true"));
        selenoidOptions.put("enableVideo", config.enableVideo());
        selenoidOptions.put("enableVNC", config.enableVNC());
        options.setCapability("selenoid:options", selenoidOptions);

        return options;
    }

    private static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public static WebDriverConfig getWebDriverConfig() {
        return config;
    }
}
