package aliexpress.page;

import ui.Browser;

public class CartPage extends Browser {

    private static String DELETE = "Удалить";
    private static String DELETE_BUTTON_LOCATOR = "//a[@class='remove-single-product']";
    private static String EMPTY_CART_TITLE = "Ваша корзина пока пуста";

    public boolean isProductPresent() {
        return isElementPresent(DELETE);
    }

    public void clickDeleteButton() {
        waitForElementVisible(DELETE_BUTTON_LOCATOR);
        click(DELETE_BUTTON_LOCATOR);
    }

    public boolean isCardEmpty() {
        return isElementPresent(EMPTY_CART_TITLE);
    }
}
