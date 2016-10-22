package yandex.service;

import yandex.bo.Account;
import yandex.page.LoginPage;

public class LoginService {
    private LoginPage loginPage = new LoginPage();

    public void login(Account account) {
        loginPage
                .openYandex()
                .sendKeysToLogin(account.getLogin())
                .sendKeysToPassword(account.getPassword())
                .signIn();
    }
}
