package mail.service;

import mail.page.InboxPage;
import mail.page.NewMailPage;

public class NewMailPageService {

    private static String NEW_MAIL = "Новое письмо";
    private NewMailPage newMailPage = new NewMailPage();
    private InboxPage inboxPage = new InboxPage();

    public boolean isCorrectNewMailPage() {
        String title = newMailPage.getNewMailPageTitle();
        return title.contains(NEW_MAIL);
    }

    public void sendMail(String address, String subject, String text) {
        inboxPage.clickToNewMessageButton();
        newMailPage.sendKeysToAddress(address);
        newMailPage.sendKeysToSubject(subject);
        newMailPage.sendKeysToMailBody(text);
        newMailPage.clickSendButton();
    }

    public boolean isMailSend() {
        return newMailPage.isMailSendPresent();
    }

    public void clickToInBox() {
        newMailPage.clickToInbox();
    }

}
