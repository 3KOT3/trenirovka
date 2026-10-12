package webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class WsWelcomPage {

    private final ElementsCollection categoryProductMenu = $$("div.header-menu .top-menu li  a");
    private final ElementsCollection subCategoryProductMenu = $$("div.header-menu .top-menu li  a");


    public WsWelcomPage productCategory(String Categoty) {
        categoryProductMenu.findBy(exactText(Categoty)).hover();
        return this;
    }

    public WsProductListPage clickSubProductCategory(String subCategoty) {
        subCategoryProductMenu.findBy(exactText(subCategoty)).click();
        return page(WsProductListPage.class);
    }
}
