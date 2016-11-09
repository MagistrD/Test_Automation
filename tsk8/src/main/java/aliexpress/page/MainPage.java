package aliexpress.page;

import ui.Browser;

public class MainPage extends Browser {
    private static String LOGIN_LOCATOR = "//*[contains(text(), 'vlad')]";
    private static String SEE_ALL_LOCATOR = "//*[contains(text(), 'Смотреть все')]";

    public boolean isUserNamePresent() {
        return isElementPresent(LOGIN_LOCATOR);
    }

    public void clickAllCategories() {
        waitForElementVisible(SEE_ALL_LOCATOR);
        click(SEE_ALL_LOCATOR);
    }

}
