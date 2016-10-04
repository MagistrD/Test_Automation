package test.mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.mail.HomePage;
import page.mail.LoginPage;

import static runner.driver.TimeOutsEnum.IMPLICIT_WAIT;
import static runner.driver.TimeOutsEnum.PAGE_LOAD;

public class SendMailTest {
    private WebDriver driver;
    private HomePage homePage;

    private static final String MAIL_RU = "https://mail.ru";
    private static final String INBOX_MASSAGES = "https://e.mail.ru/messages/inbox/";
    private static final String SENT_MESSAGES = "https://e.mail.ru/messages/sent/";
    private static final String CORRECT_LOGIN = "magistr-dante@mail.ru";
    private static final String INCORRECT_LOGIN = "magistr-nodante";

    private static final String CORRECT_PASSWORD = "09052011mail.ru";
    private static final String SUBJECT = "test";
    private static final String MESSAGE = "test test";


    @BeforeMethod
    private void setUpDriverAndLogin() {
        driver = new FirefoxDriver();
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getTimeUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getTimeUnit());
        driver.get(MAIL_RU);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(CORRECT_LOGIN, CORRECT_PASSWORD);
    }

    @Test
    public void sendMailWithAllAttributesAndCheckItInSendTest() {
        homePage.sendMsgWithAllAttributes(CORRECT_LOGIN, SUBJECT, MESSAGE);
        driver.get(INBOX_MASSAGES);
        Assert.assertTrue(homePage.isUnreadMailPresented(), "Excepted true");
    }

    @Test(priority = 1)
    public void checkInFolderSendTest() {
        Assert.assertTrue(homePage.isUnreadMailPresented(), "Excepted true");
    }

    @Test(priority = 2)
    public void checkSendMailWithoutSubjectAndBodyTest() {
        Assert.assertTrue(homePage.sendMailWithoutSubjectAndBody(CORRECT_LOGIN, null, null), "Excepted true");
    }

    @Test(priority = 3)
    public void sendMailWithoutAddressTest() {
        Assert.assertTrue(homePage.sendMailWithoutAddress(null, SUBJECT, MESSAGE), "Excepted true");
    }

    @Test(priority = 4)
    public void draftMailTest() {
        Assert.assertTrue(homePage.draftMail(CORRECT_LOGIN, SUBJECT, MESSAGE), "Expected true");
    }
}
