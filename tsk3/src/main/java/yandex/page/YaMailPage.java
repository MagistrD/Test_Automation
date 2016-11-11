package yandex.page;

import ui.Browser;

public class YaMailPage extends Browser {

    private static final String YANDEX_DISK_BUTTON_LOCATOR = "//a[@data-id='disk']";
    private static final String YANDEX_URL = "https://disk.yandex.ru/client/disk";

    public boolean isUserNamePresent(String locator) {
        return isElementPresent(locator);
    }

    public void clickYaDisk() {
        waitForElementIsPresent(YANDEX_DISK_BUTTON_LOCATOR);
        open(YANDEX_URL);
    }
}
