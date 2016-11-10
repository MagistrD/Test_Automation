package aliexpress.service;

import aliexpress.page.ProductPage;

public class ProductPageService {
    private static String PRODUCT_NAME = "Lenovo LEMON K3";

    private ProductPage productPage = new ProductPage();

    public void addProductToCart(String color, String from) {
        productPage.selectColor(color);
        productPage.selectSendFrom(from);
        productPage.clickAddToCartButton();
    }

    public boolean isGoToCartButtonPresent() {
        return productPage.isGoToCartButtonPresent();
    }

    public void goToCart() {
        productPage.clickGoToCartButton();
    }

    public boolean isCorrectProductPage() {
        String title = productPage.getTitle();
        return title.contains(PRODUCT_NAME);
    }
}
