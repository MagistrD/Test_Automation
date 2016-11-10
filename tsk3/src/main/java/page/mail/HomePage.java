package page.mail;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class HomePage {

    private static final int WAIT_TIME = 60;
    private static final int LENGTH = 30;
    private static final String INBOX_MESSAGES = "https://e.mail.ru/messages/inbox/";
    private static final String DRAFT_MESSAGES = "https://e.mail.ru/messages/drafts/";
    private static final String TRASH_MESSAGES = "https://e.mail.ru/messages/trash/";
    private static final By NEW_MAIL_BUTTON_LOCATOR = By.
            xpath("//span[@class='b-toolbar__btn__text b-toolbar__btn__text_pad']");
    private static final By ADDRESS_LOCATOR = By.xpath("//textarea[@data-original-name='To']");
    private static final By SUBJECT_LOCATOR = By.xpath("//input[@name='Subject']");
    private static final By SEND_BUTTON_LOCATOR = By.xpath("//div[@data-name='send']");
    private static final By SAVE_DRAFT_LOCATOR = By.xpath("//div[@data-name='saveDraft']");
    private static final By ACCEPT_SEND_LOCATOR = By.xpath("//div[@class='is-compose-empty_in']//form//div//button//"
            +
            "span[contains(text(),'Продолжить')]");
    private static final By SAVE_STATUS_LOCATOR = By.xpath("//a[contains(text(),'черновиках')]");
    private static final By CHECKBOX_ALL_LOCATOR = By.xpath("//div[@class='js-checkbox b-checkbox b-checkbox_"
            +
            "transparent b-checkbox_false js-shortcut']//div");
    private static final By DELETE_BUTTON_LOCATOR = By.xpath("//div[@data-shortcut-title='Del']");
    private static final By LABEL_YOU_HAVE_NOT_DRAFTS_LOCATOR = By.xpath("//span[@class='b-datalist__empty__text']");
    private static final By CLEAR_TRASH_BUTTON_LOCATOR = By.xpath("//button[@data-name='clearFolder']");
    private static final By CLEAR_FOLDER_ACCEPT_BUTTON_LOCATOR = By.xpath("//span[contains(text(),'Да')]");
    private static final By MAIL_WITHOUT_SUBJECT_LOCATOR_LOCATOR = By.xpath("//div[contains(text(), 'Без темы')]");

    private WebDriver driver;

    private String subject;
    private String message;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private String randomString() {
        String alphabet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890 .,;-";
        Random random = new Random();
        int length = 0;
        while (length == 0) {
            length = random.nextInt(LENGTH);
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
        WebElement addressInput = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(ADDRESS_LOCATOR));
        addressInput.sendKeys(address);
    }

    private void sendKeysToSubject() {
        subject = randomString();
        driver.findElement(SUBJECT_LOCATOR).sendKeys(subject);

    }

    private boolean isSubjectMatches(String subj) {
        WebElement sub = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//div[contains(text(),'" + subj + "')]")));
        return sub != null;
    }

    private boolean isMessageMatches(String msg) {
        WebElement mess = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.visibilityOfElementLocated(By.
                xpath("//span[contains(text(),'" + msg + "')]")));
        return mess != null;
    }

    public boolean isMailMatches() {
        return isSubjectMatches(subject) && isMessageMatches(message);
    }

    private void sendKeysToMailBody() {
        message = randomString();
        WebElement textFrame = driver.findElement(By.xpath("//iframe[contains(@id, 'composeEditor_ifr')]"));
        driver.switchTo().frame(textFrame);
        WebElement textBox = driver.findElement(By.xpath("//*[@id='tinymce']"));
        Actions actions = new Actions(driver);
        actions.click(textBox).sendKeys(message).build().perform();
        driver.switchTo().defaultContent();
    }

    private void clickOnSendMailButton() {
        driver.findElement(SEND_BUTTON_LOCATOR).click();
    }

    private void acceptSendEmptyLater() {
        WebElement acceptSend = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(ACCEPT_SEND_LOCATOR));
        acceptSend.click();
    }

    public void sendMail(String address) {
        clickOnNewMailButton();
        sendKeysToAddress(address);
        sendKeysToSubject();
        sendKeysToMailBody();
        clickOnSendMailButton();
    }

    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean sendMailWithOnlyAddress(String address) {
        clickOnNewMailButton();
        sendKeysToAddress(address);
        clickOnSendMailButton();
        acceptSendEmptyLater();
        driver.get(INBOX_MESSAGES);
        driver.navigate().refresh();
        WebDriverWait subj = new WebDriverWait(driver, WAIT_TIME);
        if (subj.until(ExpectedConditions.visibilityOfElementLocated(MAIL_WITHOUT_SUBJECT_LOCATOR_LOCATOR))
                != null) {
            clickCheckBox();
            clickDelete();
            return true;
        } else {
            return false;
        }

    }

    private void clickSaveButton() {
        WebElement saveButton = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(SAVE_DRAFT_LOCATOR));
        saveButton.click();
    }

    private void waitingForSaveMail() {
        WebElement labelMailIsSave = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(SAVE_STATUS_LOCATOR));
        labelMailIsSave.click();
    }

    private void clickCheckBox() {
        WebElement checkBox = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(CHECKBOX_ALL_LOCATOR));
        checkBox.click();
    }

    private void clickDelete() {
        WebElement deleteButton = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(DELETE_BUTTON_LOCATOR));
        deleteButton.click();
    }

    private void clickClearTrash() {
        WebElement clearButton = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(CLEAR_TRASH_BUTTON_LOCATOR));
        clearButton.click();
    }

    private void clickAcceptClear() {
        WebElement accept = new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.
                visibilityOfElementLocated(CLEAR_FOLDER_ACCEPT_BUTTON_LOCATOR));
        accept.click();
    }

    public boolean draftMail(String address) {
        clickOnNewMailButton();
        sendKeysToAddress(address);
        sendKeysToSubject();
        sendKeysToMailBody();
        clickSaveButton();
        waitingForSaveMail();
        driver.get(DRAFT_MESSAGES);
        isMailMatches();
        clickCheckBox();
        clickDelete();
        WebDriverWait iWait = new WebDriverWait(driver, WAIT_TIME);
        iWait.until(ExpectedConditions.visibilityOfElementLocated(LABEL_YOU_HAVE_NOT_DRAFTS_LOCATOR));
        driver.get(TRASH_MESSAGES);
        clickCheckBox();
        clickClearTrash();
        clickAcceptClear();
        return driver.findElement(CHECKBOX_ALL_LOCATOR) != null;
    }
}
