package yandex.service;

import yandex.page.YaMailPage;

public class YaMailService {
    private YaMailPage yaMailPage = new YaMailPage();

    public void getYaDisk() {
        yaMailPage.clickYaDisk();
    }

    public boolean isUserNamePresent(String s) {
        return yaMailPage.isUserNamePresent(s);
    }

    public void closeBrowser() {
        yaMailPage.close();
    }
}
