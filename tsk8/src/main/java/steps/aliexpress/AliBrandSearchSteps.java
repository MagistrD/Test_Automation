package steps.aliexpress;

import aliexpress.service.ItemPageService;
import aliexpress.service.ProductPageService;
import logging.Log;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class AliBrandSearchSteps {
    private ItemPageService itemPageService = new ItemPageService();
    private ProductPageService productPageService = new ProductPageService();

    @When("I type brand to search")
    public void typeBrand() {
        itemPageService.searchByBrand();
        Log.info("Brand searching");
    }

    @Given("Brand page is available")
    @Then("I see brand page")
    public void checkCorrectItemPage() {
        Assert.assertTrue("Incorrect Brand search", itemPageService.isCorrectBrandPage());
    }

    @When("I select product")
    public void selectProduct() {
        itemPageService.selectProduct();
        Log.info("Product select");
    }

    @Given("Product cart is available")
    @Then("I see product page")
    public void checkCorrectProductPage() {
        Assert.assertTrue("Incorrect product", productPageService.isCorrectProductPage());
    }
}
