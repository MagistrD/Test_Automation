package yandex.service;

import yandex.page.YaMailPage;

public class YaMailService {
    private static final String AUTHORIZED_USERNAME_LOCATOR = "//div[@class='mail-User-Name']";

    private YaMailPage yaMailPage = new YaMailPage();

    public void getYaDisk() {
        yaMailPage.clickYaDisk();
    }

    public boolean isUserNamePresent() {
        return yaMailPage.isUserNamePresent(AUTHORIZED_USERNAME_LOCATOR);
    }

    public void closeBrowser() {
        yaMailPage.close();
    }
}
