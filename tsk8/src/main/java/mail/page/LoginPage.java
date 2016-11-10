package mail.page;

import ui.Browser;

public class LoginPage extends Browser {

    private static String MAIL_URL = "https://mail.ru";
    private static String LOGIN_LOCATOR = "//input[@name='Login']";
    private static String PASSWORD_LOCATOR = "//input[@type='password']";
    private static String SUBMIT_BUTTON_LOCATOR = "//*[@type='submit']";

    public LoginPage openLoginPage() {
        open(MAIL_URL);
        return this;
    }

    public LoginPage sendKeysToLogin(String login) {
        typeValue(LOGIN_LOCATOR, login);
        return this;
    }

    public LoginPage sendKeysToPassword(String password) {
        typeValue(PASSWORD_LOCATOR, password);
        return this;
    }

    public void clickSubmit() {
        click(SUBMIT_BUTTON_LOCATOR);
        switchToDefault();
    }
}
