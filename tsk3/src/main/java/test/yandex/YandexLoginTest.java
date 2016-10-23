package test.yandex;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import yandex.bo.Account;
import yandex.service.LoginService;
import yandex.service.YaMailService;

public class YandexLoginTest {

    private static final String LOGIN = "vlad.litoshik@yandex.ru";
    private static final String PASSWORD = "litoshik";
    private YaMailService yaMailService;

    @Test
    public void correctLoginTest() {
        LoginService loginService = new LoginService();
        yaMailService = new YaMailService();
        Account account = new Account(LOGIN, PASSWORD);
        loginService.login(account);

        Assert.assertTrue(yaMailService.isUserNamePresent(LOGIN), "Incorrect login");
    }

    @AfterTest
    public void quit() {
        yaMailService.closeBrowser();
    }
}
