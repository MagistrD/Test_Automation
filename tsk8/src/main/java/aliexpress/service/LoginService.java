package aliexpress.service;

import aliexpress.page.LoginPage;

public class LoginService {

    private LoginPage loginPage = new LoginPage();

    public void logIn() {
        loginPage.openLoginPage();
        loginPage.sendKeysToLogin();
        loginPage.sendKeysToPassword();
        loginPage.clickSubmit();
    }
}
