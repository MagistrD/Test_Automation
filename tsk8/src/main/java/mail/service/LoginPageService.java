package mail.service;

import mail.page.LoginPage;

public class LoginPageService {

    private LoginPage loginPage = new LoginPage();

    public void login(String login, String password) {
        loginPage.openLoginPage();
        loginPage.sendKeysToLogin(login);
        loginPage.sendKeysToPassword(password);
        loginPage.clickSubmit();
    }
}
