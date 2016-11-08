package steps;

import aliexpress.service.CartPageService;
import aliexpress.service.ProductPageService;
import logging.Log;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class AliCartSteps {
    CartPageService cartPageService = new CartPageService();
    ProductPageService productPageService = new ProductPageService();

    @When("I go to cart")
    public void goToCart() {
        productPageService.goToCart();
        Log.info("Cart open");
    }

    @Then("I see product")
    public void checkProductInCart() {
        Assert.assertTrue("Product not add to cart", cartPageService.isProductInCart());
    }

    @When("I delete product from cart")
    public void deleteProductFromCart() {
        cartPageService.deleteProductFromCart();
        Log.info("Product deleted from cart");
    }

    @Then("I didn't see product in cart")
    public void checkDeletingProduct() {
        Assert.assertTrue("Product not deleting", cartPageService.isProductDeleteFromCart());
    }

    @AfterStories
    public void closeBrowser(){
    }


}
