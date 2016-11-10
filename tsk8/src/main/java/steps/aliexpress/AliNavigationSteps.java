package steps.aliexpress;

import aliexpress.service.CategoryPageService;
import aliexpress.service.MainPageService;
import logging.Log;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class AliNavigationSteps {
    private MainPageService mainPageService = new MainPageService();
    private CategoryPageService categoryPageService;

    @When("I select category and item from navigation")
    public void selectItem() {
        Log.debug("click all categories");
        mainPageService.clickItem();
        Log.info("Select category");
        categoryPageService = new CategoryPageService();
    }

    @Then("I see item page")
    public void checkCorrectPage() {
        Assert.assertTrue("Incorrect Item page", categoryPageService.isCorrectTitle());
    }
}
