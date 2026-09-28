package webshop;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allureSelenide", new AllureSelenide());
    }
}
