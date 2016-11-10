package steps.mail;

import mail.service.InboxPageService;
import mail.service.NewMailPageService;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class MailNewLetterSteps {
    InboxPageService inboxPageService = new InboxPageService();
    private NewMailPageService newMailPageService = new NewMailPageService();

    @When("I send new message to <mail> with <subject> and <text>")
    public void sendNewMail(@Named("mail") String to, @Named("subject") String subject,
                            @Named("text") String text) {
        newMailPageService.sendMail(to, subject, text);
    }

    @Then("I want to see that mail sent")
    public void checkIsMailSend() {
        Assert.assertTrue("Mail not send", newMailPageService.isMailSend());
    }
}
