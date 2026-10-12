package webshop.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class WsCartPage {

    private final SelenideElement itemNameCart = $("a.product-name");
    private final SelenideElement quantityCart = $("input.qty-input");
    private final SelenideElement totalAmountCart = $("span.product-subtotal");
    private final SelenideElement unitPriceCart = $("span.product-unit-price");


    public WsCartPage checkItemNameInCart(String itemName) {
        itemNameCart.shouldHave(text(itemName));
        return this;
    }

    public String saveQuantityInCart() {
        String text = quantityCart.getAttribute("value");
        return text;
    }

    public WsCartPage CheckTotalAmountInCart(String itemPrice, String itemQuantity) {
        totalAmountCart.shouldHave(exactText(String.valueOf(
                Float.parseFloat(itemPrice) * Float.parseFloat(itemQuantity))));
        return this;
    }

    @Step("Проверить наименование товара в корзине")
    public String getItemName(){
        return itemNameCart.getText();
    }

    @Step("Проверить количесство товара в корзине")
    public String getQuantity() {
        return quantityCart.getValue();
    }

    @Step("Проверить стоимость одного товара в корзине")
    public String getSubtotal(){
        return totalAmountCart.getText();
    }

    @Step("Проверить общую стоимость товаров в корзине")
    public String getUnitPrice() {
        return unitPriceCart.getText();
    }

}

