package mail.page;

import ui.Browser;

public class InboxPage extends Browser {

    private static final String NEW_MAIL_BUTTON_LOCATOR = "//span[contains(text(),'Написать письмо')]";
    private static final String AUTHOR = "Влад Magistr";

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
