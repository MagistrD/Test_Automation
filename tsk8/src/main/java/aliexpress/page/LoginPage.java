package aliexpress.page;


import ui.Browser;

public class LoginPage extends Browser {
    private static String ALIEXPRESS_LOGIN_URL = "https://login.aliexpress.com/buyer_ru.htm";
    private static String LOGIN_LOCATOR = "//input[@name='loginId']";
    private static String PASSWORD_LOCATOR = "//input[@type='password']";
    private static String SUBMIT_BUTTON_LOCATOR = "//*[@type='submit']";
    private static String LOGIN_FRAME_LOCATOR = "//iframe[contains(@id, 'alibaba-login-box')]";
    private static String LOGIN = "vlad.litoshik@yandex.ru";
    private static String PASSWORD = "aliexpress";


    public LoginPage openLoginPage() {
        open(ALIEXPRESS_LOGIN_URL);
        return this;
    }

    public LoginPage sendKeysToLogin() {
        switchToFrame(LOGIN_FRAME_LOCATOR);
        typeValue(LOGIN_LOCATOR, LOGIN);
        return this;
    }

    public LoginPage sendKeysToPassword() {
        typeValue(PASSWORD_LOCATOR, PASSWORD);
        return this;
    }

    public void clickSubmit() {
        click(SUBMIT_BUTTON_LOCATOR);
        switchToDefault();
    }

}
