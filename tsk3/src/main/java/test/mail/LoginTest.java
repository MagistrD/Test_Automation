package test.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import page.mail.ErrorLoginPage;
import page.mail.LoginPage;

import static runner.driver.TimeOutsEnum.IMPLICIT_WAIT;
import static runner.driver.TimeOutsEnum.PAGE_LOAD;

public class LoginTest {

    private static final String CORRECT_LOGIN = "magistr-dante@mail.ru";
    private static final String CORRECT_PASSWORD = "09052011mail.ru";
    private static final String LONG_STRING = "123456789012345678901234lsdughldgldsfgsdjg234234234234234234234";
    private static final int WAIT_TIME = 20;
    private static final String MAIL_RU = "https://mail.ru";
    private static final By AUTHORISATION_USER_EMAIL_LOCATOR = By.xpath("//i[@id='PH_user-email']");
    private WebDriver driver;

    @BeforeMethod
    private void setUpDriver() {
        setUp();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getTimeUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getTimeUnit());
    }

    public void login() {
        driver.get(MAIL_RU);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(CORRECT_LOGIN, CORRECT_PASSWORD);
    }

    @Test
    public void loginToMailWithCorrectPasswordTest() {
        login();
        WebElement userLogin = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(AUTHORISATION_USER_EMAIL_LOCATOR));
        Assert.assertEquals(userLogin.getText(), CORRECT_LOGIN);
    }

    @DataProvider(name = "Data for incorrect login")
    public Object[][] dataForIncorrectLogin() {
        return new Object[][]{
                {"sfgsdjgsdg93256^*%", "!@#$%^&*"},
                {"甲骨文", "oew634925"},
                {CORRECT_LOGIN, "甲骨文"},
                {"甲骨文", CORRECT_PASSWORD},
                {LONG_STRING, LONG_STRING}
        };
    }

    @Test(dataProvider = "Data for incorrect login")
    public void loginToMailWithInCorrectPasswordTest(String login, String password) {
        driver.get(MAIL_RU);
        LoginPage loginPage = new LoginPage(driver);
        ErrorLoginPage errorLoginPage = loginPage.errorLoginPage(login, password);
        Assert.assertTrue(errorLoginPage.isErrorMsg(), "Expected incorrect loginTest or password");
    }

    @AfterMethod
    public void killDriver() {
        driver.quit();
    }
}
