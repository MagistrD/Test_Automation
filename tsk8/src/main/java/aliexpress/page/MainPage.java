package aliexpress.page;

import ui.Browser;

public class MainPage extends Browser {
    private static final String LOGIN_LOCATOR = "vlad";
    private static final String SEE_ALL_LOCATOR = "//*[contains(text(), 'Смотреть все')]";

    public boolean isUserNamePresent() {
        return isElementPresent(LOGIN_LOCATOR);
    }

    public void clickAllCategories() {
        waitForElementVisible(SEE_ALL_LOCATOR);
        click(SEE_ALL_LOCATOR);
    }
}
