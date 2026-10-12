package webshop;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import webshop.config.WebDriverConfig;
import webshop.util.AttachManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static webshop.config.Config.getSelenoidChromeOptions;
import static webshop.config.Config.getWebDriverConfig;

public class TestBase {

    private static final WebDriverConfig config = getWebDriverConfig();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allureSelenide", new AllureSelenide());

        Configuration.browser = config.browser();

        String property = System.getProperty("run", "local");

        if ("remote".equals(System.getProperty("run"))) {
            Configuration.remote = "https://" + config.selenoidUser() + ":" + config.selenoidPassword() + "@" + config.selenoidUrl();
            Configuration.browserCapabilities = getSelenoidChromeOptions();   //  отдаём настройки selenide

        }
    }


    @AfterEach
    void afterAll() {

        if("remote".equals(config.run()))
        AttachManager.addVideo(); // добауляем видео в отчет allure
    }
}
