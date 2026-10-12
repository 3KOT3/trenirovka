package webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class WsProductListPage {

    private final ElementsCollection checkSubCategoryPage = $$("div.breadcrumb ul li");
    private final ElementsCollection openProductSubCategoryPage = $$("div.product-item .product-title");
    private final SelenideElement itemName = $("[itemprop=name]");
    private final SelenideElement itemPrice = $("[itemprop=price]");
    public static final String PRODUCT_NAME = "Build your own computer";

    public WsProductListPage checkSubCategoryProductsPage() {
        checkSubCategoryPage.shouldHave(texts("Home", "Computers", "Desktops"));
        return this;
    }

    public WsConcreteProductPage openProduct(String productName) {
        openProductSubCategoryPage.findBy(exactText(productName)).click();
        return page(WsConcreteProductPage.class);
    }

    public String getItemName() {
        return itemName.getText();
    }

    public String getItemPrice() {
        return itemPrice.getText();
    }
}
