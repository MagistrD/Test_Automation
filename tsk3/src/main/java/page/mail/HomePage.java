package page.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class HomePage {

    private static final int WAIT_TIME = 60;
    private static final String INBOX_MASSAGES = "https://e.mail.ru/messages/inbox/";
    private static final String SENT_MESSAGES = "https://e.mail.ru/messages/sent/";
    private static final String DRAFT_MESSAGES = "https://e.mail.ru/messages/drafts/";
    private static final String TRASH_MESSAGES = "https://e.mail.ru/messages/trash/";
    private static final By NEW_MAIL_BUTTON_LOCATOR = By.
            xpath("//span[@class='b-toolbar__btn__text b-toolbar__btn__text_pad']");
    private static final By ADDRESS_LOCATOR = By.xpath("//textarea[@data-original-name='To']");
    private static final By SUBJECT_LOCATOR = By.xpath("//input[@name='Subject']");
    private static final By SEND_BUTTON_LOCATOR = By.xpath("//div[@data-name='send']");
    private static final By UNREAD_POINT_LOCATOR = By.xpath("//*[contains(@class,'b-letterstatus b-letterstatus_unread')]");

    private static final By SAVE_DRAFT_LOCATOR = By.xpath("//div[@data-name='saveDraft']");
    private WebDriver driver;

    private String subject;
    private String message;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String randomString() {
        String alphabet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890 .,;-";
        Random random = new Random();
        int length = 0;
        while (length == 0) {
            length = random.nextInt(30);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return stringBuilder.toString();
    }

    private void clickOnNewMailButton() {
        WebElement newMailButton = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(NEW_MAIL_BUTTON_LOCATOR));
        newMailButton.click();
    }

    private void sendKeysToAddress(String address) {
        driver.findElement(ADDRESS_LOCATOR).sendKeys(address);
    }

    private void sendKeysToSubject() {
        subject = randomString();
        driver.findElement(SUBJECT_LOCATOR).sendKeys(subject);

    }

    private boolean isSubjectMatches(String subject) {
        WebElement sub = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + subject + "')]")));
        if (sub != null) {
            return true;
        }
        return false;
    }

    private boolean isMessageMatches(String message) {
        WebElement mess = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + message + "')]")));
        if (mess != null) {
            return true;
        }
        return false;
    }

    public boolean isMailMatches() {
        if (isSubjectMatches(subject) && isMessageMatches(message)) {
            return true;
        }
        return false;
    }

    private void sendKeysToMailBody() {
        message = randomString();
        WebElement textFrame = driver.findElement(By.xpath("//iframe[contains(@id, '_composeEditor_ifr')]"));
        driver.switchTo().frame(textFrame);
        WebElement textBox = driver.findElement(By.xpath("//*[@id='tinymce']"));
        Actions actions = new Actions(driver);
        actions.click(textBox).sendKeys(message).build().perform();
        driver.switchTo().defaultContent();
    }

    private void clickOnSendMailButton() {
        driver.findElement(SEND_BUTTON_LOCATOR).click();
    }

    public void sendMail(String address) {
        clickOnNewMailButton();
        sendKeysToAddress(address);
        sendKeysToSubject();
        sendKeysToMailBody();
        clickOnSendMailButton();
    }

    public boolean isUnreadMailPresent() {
        driver.navigate().refresh();
        WebElement unreadPoint = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(UNREAD_POINT_LOCATOR));
        boolean present;
        try {
            unreadPoint.click();
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }
        return present;
    }

    public boolean sendMailWithoutAddress(String address, String subject, String message) {
        sendMail(null);
        WebDriverWait waitForAlert = new WebDriverWait(driver, WAIT_TIME);
        return waitForAlert.until(ExpectedConditions.alertIsPresent()) != null;
    }

    public void sendMailWithOnlyAddress(String address) {
        clickOnNewMailButton();
        sendKeysToAddress(address);
        clickOnSendMailButton();
    }

    public boolean sendMailWithoutSubjectAndBody(String address, String subject, String message) {
        boolean b = false;
        sendMail(address);
        driver.findElement(By.xpath("//div[@class='is-compose-empty_in']//span")).click();
        driver.get(INBOX_MASSAGES);
        if (isUnreadMailPresent()) {
            driver.get(SENT_MESSAGES);
            WebElement mailList = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                    visibilityOfElementLocated(By.xpath("//div[@class='b-datalist__item js-datalist-item']")));
            if (mailList != null) {
                driver.findElement(By.xpath("//div[@class='b-checkbox__box']")).click();
                driver.findElement(By.xpath("//div[@data-shortcut-title='Del']")).click();
                b = true;
            }
        }
        return b;
    }

    public boolean draftMail(String address, String subject, String message) {
        WebElement newMailButton = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(NEW_MAIL_BUTTON_LOCATOR));
        newMailButton.click();
        driver.findElement(ADDRESS_LOCATOR).sendKeys(address);
        driver.findElement(SUBJECT_LOCATOR).sendKeys(subject);
        WebElement textFrame = driver.findElement(By.xpath("//iframe[contains(@id, '_composeEditor_ifr')]"));
        driver.switchTo().frame(textFrame);
        WebElement textBox = driver.findElement(By.xpath("//*[@id='tinymce']"));
        Actions actions = new Actions(driver);
        actions.click(textBox).sendKeys(message);
        driver.switchTo().defaultContent();
        WebElement saveDraft = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(SAVE_DRAFT_LOCATOR));
        saveDraft.click();
        driver.get(DRAFT_MESSAGES);
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
        WebElement checkbox = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(By.
                        xpath("//div[@class='js-checkbox b-checkbox b-checkbox_transparent "
                                +
                                "b-checkbox_false js-shortcut']//div")));
        checkbox.click();
        driver.findElement(By.xpath("//div[@data-shortcut-title='Del']")).click();
        driver.get(TRASH_MESSAGES);
        driver.findElement(By.xpath("//button[@data-name='clearFolder']")).click();
        driver.findElement(By.xpath("//button[@class='btn btn_stylish confirm-ok']")).click();
        return driver.findElement(By.xpath("//div[@class='b-datalist__item__panel']")) != null;
    }
}
