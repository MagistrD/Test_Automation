package aliexpress.service;

import aliexpress.page.CartPage;

public class CartPageService {

    private CartPage cartPage = new CartPage();

    public boolean isProductInCart() {
        return cartPage.isProductPresent();
    }

    public void deleteProductFromCart() {
        cartPage.clickDeleteButton();
    }

    public boolean isProductDeleteFromCart() {
        return cartPage.isCardEmpty();
    }

    public void closeBrowser() {
        cartPage.close();
    }
}
