package aliexpress.page;

import ui.Browser;

public class ProductPage extends Browser {
    private static String COLOR_LOCATOR = "//span[contains(text(),'%s')]";
    private static String SEND_FROM_LOCATOR = "//span[contains(text(),'%s')]";
    private static String ADD_TO_CART_LOCATOR = "//a[@class='add-cart-btn']";
    private static String GO_TO_CART = "Перейти к корзине";
    private static String GO_TO_CART_BUTTON_LOCATOR = "//a[@class='ui-button ui-button-normal ui-button-medium']";

    public void selectColor(String color) {
        waitForElementVisible(String.format(COLOR_LOCATOR, color));
        click(String.format(COLOR_LOCATOR, color));
    }

    public void selectSendFrom(String from) {
        waitForElementVisible(String.format(SEND_FROM_LOCATOR, from));
        click(String.format(SEND_FROM_LOCATOR, from));
    }

    public void clickAddToCartButton() {
        waitForElementVisible(ADD_TO_CART_LOCATOR);
        click(ADD_TO_CART_LOCATOR);
    }

    public boolean isGoToCartButtonPresent() {
        return isElementPresent(GO_TO_CART);
    }

    public void clickGoToCartButton() {
        waitForElementVisible(GO_TO_CART_BUTTON_LOCATOR);
        click(GO_TO_CART_BUTTON_LOCATOR);
    }

    public String getTitle() {
        return getPageTitle();
    }

}
