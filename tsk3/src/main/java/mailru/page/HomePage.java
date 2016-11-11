package mailru.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    private static final By UNREAD_POINT_LOCATOR = By.xpath("//*[contains(@class,'b-letterstatus "
            + "b-letterstatus_unread')]");

    private static final By SAVE_DRAFT_LOCATOR = By.xpath("//div[@data-name='saveDraft']");
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private void trySendMail(String address, String subject, String message) {
        WebElement newMailButton = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(NEW_MAIL_BUTTON_LOCATOR));
        newMailButton.click();
        driver.findElement(ADDRESS_LOCATOR).sendKeys(address);
        driver.findElement(SUBJECT_LOCATOR).sendKeys(subject);
        WebElement textFrame = driver.findElement(By.xpath("//iframe[contains(@id, '_composeEditor_ifr')]"));
        driver.switchTo().frame(textFrame);
        WebElement textBox = driver.findElement(By.xpath("//*[@id='tinymce']"));
        Actions actions = new Actions(driver);
        actions.click(textBox).sendKeys(message).build().perform();
        driver.switchTo().defaultContent();
        driver.findElement(SEND_BUTTON_LOCATOR).click();
    }

    public void sendMsgWithAllAttributes(String address, String subject, String message) {
        trySendMail(address, subject, message);
    }

    public boolean checkUnreadMail() {
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
        trySendMail(address, subject, message);
        WebDriverWait waitForAlert = new WebDriverWait(driver, WAIT_TIME);
        return waitForAlert.until(ExpectedConditions.alertIsPresent()) != null;
    }

    public boolean sendMailWithoutSubjectAndBody(String address, String subject, String message) {
        boolean b = false;
        trySendMail(address, subject, message);
        driver.findElement(By.xpath("//div[@class='is-compose-empty_in']//span")).click();
        driver.get(INBOX_MASSAGES);
        if (checkUnreadMail()) {
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
