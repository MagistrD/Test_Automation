package steps.mail;

import mail.service.InboxPageService;
import mail.service.NewMailPageService;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class MailInboxSteps {

    private NewMailPageService newMailPageService = new NewMailPageService();
    private InboxPageService inboxPageService = new InboxPageService();

    @When("I go to inbox mail")
    public void goToInbox() {
        newMailPageService.clickToInBox();
    }

    @Then("I see sent mail")
    public void checkThatMailSend() {
        Assert.assertTrue("Letter didn't come", inboxPageService.isMailCome());
    }

}
