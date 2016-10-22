package yandex.page;

import ui.Browser;

public class LoginPage extends Browser {
    private static final String URL = "https://www.yandex.by/";
    private static final String LOGIN_INPUT_LOCATOR = "//input[@name='login']";
    private static final String PASSWORD_INPUT_LOCATOR = "//input[@name='passwd']";
    private static final String LOGIN_BUTTON_LOCATOR = "//button[@class='button auth__button domik2__auth-button "
            + "button_theme_bordergray button_size_m i-bem button_js_inited auth__button_color_yellow']";

    public LoginPage openYandex() {
        open(URL);
        return this;
    }

    public LoginPage sendKeysToLogin(String login) {
        typeValue(LOGIN_INPUT_LOCATOR, login);
        return this;
    }

    public LoginPage sendKeysToPassword(String password) {
        typeValue(PASSWORD_INPUT_LOCATOR, password);
        return this;
    }

    public void signIn() {
        click(LOGIN_BUTTON_LOCATOR);
    }

}
