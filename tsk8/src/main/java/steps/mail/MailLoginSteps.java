package steps.mail;

import mail.service.InboxPageService;
import mail.service.LoginPageService;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class MailLoginSteps {
    private LoginPageService loginPageService = new LoginPageService();
    private InboxPageService inboxPageService = new InboxPageService();

    @When("I type <login> and <password>")
    public void login(@Named("login") String login, @Named("password") String password) {
        loginPageService.login(login, password);
    }

    @Then("I see inbox page")
    public void checkInboxPage() {
        Assert.assertTrue("Incorrect login", inboxPageService.isCorrectPage());
    }
}
