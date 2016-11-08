package steps;

import aliexpress.service.ItemPageService;
import aliexpress.service.ProductPageService;
import logging.Log;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class AliBrandSearchSteps {
    ItemPageService itemPageService = new ItemPageService();
    ProductPageService productPageService = new ProductPageService();

    @When("I type brand to search")
    public void typeBrand() {
        itemPageService.searchByBrand();
        Log.info("Brand searching");
    }

    @Then("I see brand page")
    public void checkCorrectItemPage() {
        Assert.assertTrue("Incorrect Brand search", itemPageService.isCorrectBrandPage());
    }

    @When("I select product")
    public void selectProduct() {
        itemPageService.selectProduct();
        Log.info("Product select");
    }

    @Then("I see product page")
    public void checkCorrectProductPage() {
        Assert.assertTrue("Incorrect product",productPageService.isCorrectProductPage());
    }


}
