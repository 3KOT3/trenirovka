package webshop.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import webshop.TestBase;
import webshop.page.WsCartPage;
import webshop.page.WsConcreteProductPage;
import webshop.page.WsWelcomPage;
import webshop.steps.AuthSteps;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.open;
import static webshop.config.Config.WEBSHOP_HOST;
import static webshop.page.WsProductListPage.PRODUCT_NAME;

public class CartTest extends TestBase {
    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.RegistrationNewUser();
    }

    @Test
    @Tag("positive")
    @DisplayName("Успешное добавление товара в корзину")
    public void CartTest() {

        String processor = "2.5 GHz Intel Pentium Dual-Core E2200";
        String ramSize = "4GB";
        String hddSize = "400 GB";
        String os = "Windows 10";
        List<String> software = List.of("Microsoft Office", "Acrobat Reader", "Total Commander");
        String itemQuantity = "4";

        WsConcreteProductPage concreteProductPage = open(WEBSHOP_HOST, WsWelcomPage.class)
                .productCategory("Computers")
                .clickSubProductCategory("Desktops")
                .checkSubCategoryProductsPage()
                .openProduct(PRODUCT_NAME);

        float unitPrice = Float.parseFloat(concreteProductPage.getItemPrice())
                + processorSurcharge(processor)
                + ramSurcharge(ramSize)
                + hddSurcharge(hddSize)
                + osSurcharge(os)
                + softwareSurcharge(software);

        float expectedSubtotal = unitPrice * Integer.parseInt(itemQuantity);

        WsCartPage cartPage = concreteProductPage
                .selectProcessorClick(processor)
                .selectRamClick(ramSize)
                .selectHddClick(hddSize)
                .selectOsClick(os)
                .selectSoftwareClick(software)
                .setQuantityValue(itemQuantity)
                .addToCartClick()
                .checkNotificationAddCart()
                .checkQuantityCartInHeader(itemQuantity)
                .openCart();

        String unitPriceText = String.format(Locale.US, "%.2f", unitPrice);
        String subtotalText  = String.format(Locale.US, "%.2f", expectedSubtotal);

        assertAll(
                () -> assertEquals(PRODUCT_NAME,  cartPage.getItemName()),
                () -> assertEquals(itemQuantity,  cartPage.getQuantity()),
                () -> assertEquals(unitPriceText, cartPage.getUnitPrice()),
                () -> assertEquals(subtotalText,  cartPage.getSubtotal())
        );
    }

    private float processorSurcharge(String processorType) {
        return switch (processorType) {
            case "2.2 GHz Intel Pentium Dual-Core E2200" -> 0f;
            case "2.5 GHz Intel Pentium Dual-Core E2200" -> 15f;
            default -> throw new IllegalArgumentException("Unknown processor: " + processorType);
        };
    }

    private float ramSurcharge(String ramSize) {
        return switch (ramSize) {
            case "2 GB" -> 0f;
            case "4GB" -> 20f;
            case "20GB" -> 60f;
            default -> throw new IllegalArgumentException("Unknown ram: " + ramSize);
        };
    }

    private float hddSurcharge(String hddSize) {
        return switch (hddSize) {
            case "320 GB" -> 0f;
            case "400 GB" -> 100f;
            default -> throw new IllegalArgumentException("Unknown hdd: " + hddSize);
        };
    }

    private float osSurcharge(String os) {
        return switch (os) {
            case "Ubuntu" -> 0f;
            case "Windows 7" -> 50f;
            case "Windows 10" -> 60f;
            default -> throw new IllegalArgumentException("Unknown os: " + os);
        };
    }

    private static final Map<String, Float> SOFTWARE_SURCHARGE = Map.of(
            "Microsoft Office", 50f,
            "Acrobat Reader", 10f,
            "Total Commander", 5f
    );

    private float softwareSurcharge(List<String> selectedSoftware) {
        float total = 0f;
        for (String software : selectedSoftware) {
            Float value = SOFTWARE_SURCHARGE.get(software);
            if (value == null) {
                throw new IllegalArgumentException("Unknown software: " + software);
            }
            total += value;
        }
        return total;
    }
}