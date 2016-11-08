package steps;

import aliexpress.service.LoginService;
import aliexpress.service.MainPageService;
import logging.Log;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class AliLoginSteps {
    private LoginService loginService = new LoginService();
    private MainPageService mainPageService = new MainPageService();

    @When("I login to Aliexpress")
    public void loginToAli() {
        loginService.logIn();
        Log.info("Enter to Ali");
    }

    @Then("I see User name")
    public void checkCorrectLogin() {
        Assert.assertTrue("Incorrect login", mainPageService.isLogin());
    }
}
