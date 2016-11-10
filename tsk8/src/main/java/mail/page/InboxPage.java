package mail.page;

import ui.Browser;

public class InboxPage extends Browser {

    private static String NEW_MAIL_BUTTON_LOCATOR = "//span[contains(text(),'Написать письмо')]";
    private static String SENT_MAIL_LOCATOR = "//*[contains(text(), '%s')]";
    private static String AUTHOR = "Влад Magistr";

    public String getInboxPageTitle() {
        return getPageTitle();
    }

    public void clickToNewMessageButton() {
        waitForElementVisible(NEW_MAIL_BUTTON_LOCATOR);
        click(NEW_MAIL_BUTTON_LOCATOR);
    }

    public boolean isMailCame() {
        return isElementPresent(AUTHOR);
    }
}


