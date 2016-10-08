package test.mail;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.mail.HomePage;

public class SendMailTest {

    private static final String MAIL_RU = "https://mail.ru";
    private static final String INBOX_MASSAGES = "https://e.mail.ru/messages/inbox/";
    private static final String SENT_MESSAGES = "https://e.mail.ru/messages/sent/";
    private static final String CORRECT_LOGIN = "magistr-dante@mail.ru";
    private static final String CORRECT_PASSWORD = "09052011mail.ru";
    private WebDriver driver;
    private HomePage homePage;
    private LoginTest loginTest;

    @BeforeMethod
    private void setUpDriverAndLogin() {
        loginTest.setUp();
        homePage = new HomePage(driver);
        loginTest.login();
    }

    @Test
    public void sendMailWithAllAttributesAndCheckItInInboxTest() {
        homePage.sendMail(CORRECT_LOGIN);
        driver.get(INBOX_MASSAGES);
        driver.navigate().refresh();
        Assert.assertTrue(homePage.isMailMatches(), "Excepted true");
    }

    @Test
    public void sendMailWithAllAttributesAndCheckItInSendTest() {
        homePage.sendMail(CORRECT_LOGIN);
        driver.get(SENT_MESSAGES);
        driver.navigate().refresh();
        Assert.assertTrue(homePage.isMailMatches(), "Excepted true");
    }

    @Test
    public void sendMailWithoutSubjectAndBodyTest() {
        Assert.assertTrue(homePage.sendMailWithOnlyAddress(CORRECT_LOGIN), "Expected true");
    }

    @Test
    public void sendMailWithoutAddressTest() {
        homePage.sendMail(null);
        Assert.assertTrue(homePage.isAlertPresent(), "Excepted true");
    }

    @Test
    public void draftMailTest() {
        Assert.assertTrue(homePage.draftMail(CORRECT_LOGIN), "Expected true");
    }
}
