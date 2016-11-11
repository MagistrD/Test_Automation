package steps.aliexpress;

import aliexpress.service.ProductPageService;
import logging.Log;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class AliProductSteps {

    private ProductPageService productPageService = new ProductPageService();

    @When("I select <color> and <from> and send to Cart")
    public void addProductToCart(@Named("color") String color, @Named("from") String from) {
        productPageService.addProductToCart(color, from);
        Log.info("Product adding to card");
    }

    @Then("I see button go to cart")
    public void checkProductInCart() {
        Assert.assertTrue("Product not add to cart", productPageService.isGoToCartButtonPresent());
    }
}
