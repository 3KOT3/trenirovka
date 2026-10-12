package webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.*;

public class WsConcreteProductPage {

    private final SelenideElement itemName = $("[itemprop=name]");
    private final SelenideElement itemPrice = $("[itemprop=price]");
    private final ElementsCollection select = $$("div.attributes dl dt");
    private final SelenideElement setQuantity = $(".qty-input");
    private final SelenideElement buttonAddToCart = $(".add-to-cart-button");
    private final SelenideElement barNotification = $(".content");
    private final SelenideElement cartQuantityBadge = $("span.cart-qty");
    private final SelenideElement cartLink = $("#topcartlink a");


    public WsConcreteProductPage selectProcessorClick(String processor){
        select.findBy(text("Processor")).sibling(0).$$("select option").findBy(text(processor)).click();
        return this;
    }

    public WsConcreteProductPage selectRamClick(String ram){
        select.findBy(text("RAM")).sibling(0).$$("select option").findBy(text(ram)).click();
        return this;
    }

    public WsConcreteProductPage selectHddClick(String hdd){
        select.findBy(text("HDD")).sibling(0).$$("ul.option-list li label").findBy(text(hdd)).click();
        return this;
    }

    public WsConcreteProductPage selectOsClick(String os){
        select.findBy(text("OS")).sibling(0).$$("ul.option-list li label").findBy(text(os)).click();
        return this;
    }

    public WsConcreteProductPage selectSoftwareClick(List<String> software) {
        SelenideElement softwareBlock = select.findBy(text("Software")).sibling(0);
        ElementsCollection checkboxes = softwareBlock.$$("ul.option-list li input[type=checkbox]");

        // 1. Снять все, что отмечены
        for (SelenideElement cb : checkboxes) {
            if (cb.is(selected)) {
                cb.click();
            }
        }

        // 2. Поставить только нужные
        for (String s : software) {
            softwareBlock.$$("ul.option-list li")
                    .findBy(text(s))
                    .$("input[type=checkbox]")
                    .click();
        }
        return this;
    }

    public WsConcreteProductPage addToCartClick() {
        buttonAddToCart.click();
        return this;
    }

    public WsConcreteProductPage setQuantityValue(String quantity){
        setQuantity.setValue(quantity);
        return this;
    }

    public WsConcreteProductPage checkNotificationAddCart() {
        barNotification.shouldBe(visible)
                .shouldHave(text("The product has been added to your shopping cart"));
        return this;
    }

    public WsConcreteProductPage checkQuantityCartInHeader(String quantity) {
        cartQuantityBadge.shouldHave(exactText("(" + quantity + ")"));
        return this;
    }

    public WsCartPage openCart() {
        cartLink.click();
        return page(WsCartPage.class);
    }

    public String getItemName() {
        return itemName.getText();
    }

    public String getItemPrice() {
        return itemPrice.getText();
    }
}
