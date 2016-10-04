package test.mail;

import runner.driver.TimeOutsEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.mail.ErrorLoginPage;
import page.mail.LoginPage;

public class LoginTest {
    private WebDriver driver;

    private static final String CORRECT_LOGIN = "magistr-dante@mail.ru";
    private static final String CORRECT_PASSWORD = "09052011mail.ru";
    private static final String INCORRECT_PASSWORD = "6546545646546";
    private static final int WAIT_TIME = 20;

    private static final String MAIL_RU = "https://mail.ru";
    private static final By LOGIN_INBOX_LOCATOR = By.xpath(".//input[@id='mailbox__login']");
    private static final By PASSWORD_INBOX_LOCATOR = By.xpath(".//input[@id='mailbox__password']");
    private static final By LOGIN_SUBMIT_BUTTON_LOCATOR = By.xpath("//input[@id='mailbox__auth__button']");
    private static final By AUTHORISATION_USER_EMAIL_LOCATOR = By.xpath("//i[@id='PH_user-email']");
    private static final By LABEL_INCORRECT_USERNAME_OR_PASSWORD_LOCATOR = By.xpath(" ");

    @BeforeMethod
    private void setUpDriver() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(TimeOutsEnum.IMPLICIT_WAIT.getValue(), TimeOutsEnum.IMPLICIT_WAIT.getTimeUnit());
        driver.manage().timeouts().pageLoadTimeout(TimeOutsEnum.PAGE_LOAD.getValue(), TimeOutsEnum.PAGE_LOAD.getTimeUnit());
    }

    @Test
         public void loginToMailWithCorrectLoginAndPasswordTest() {
        loginAndCheckLogin(CORRECT_LOGIN, CORRECT_PASSWORD);
    }

    @Test
    public void loginToMailWithCorrectPasswordTest() {
        loginAndCheckLogin(CORRECT_LOGIN, CORRECT_PASSWORD);
    }

    public void loginAndCheckLogin(String login, String pass) {
        driver.get(MAIL_RU);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(login, pass);
        WebElement userLogin = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(AUTHORISATION_USER_EMAIL_LOCATOR));
        Assert.assertEquals(userLogin.getText(), login);
    }

    @Test
    public void loginToMailWithInCorrectPasswordTest() {
        driver.get(MAIL_RU);
        LoginPage loginPage = new LoginPage(driver);
        ErrorLoginPage errorLoginPage = loginPage.loginAndGetElements(CORRECT_LOGIN, INCORRECT_PASSWORD);
        Assert.assertTrue(errorLoginPage.isErrorMsg(), "Expected incorrect login or password");
    }

    @AfterClass
    public void killDriver() {
        driver.quit();
    }
}
