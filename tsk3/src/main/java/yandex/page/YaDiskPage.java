package yandex.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import ui.Browser;

public class YaDiskPage extends Browser {

    private static final String SEND_FILE_BUTTON_LOCATOR = "//input[@type='file']";
    private static final String PATH = "./tsk3/src/main/resources/files/";
    private static final String CLOSE_UPLOAD_DIALOG_BUTTON_LOCATOR = "//button[@class=' nb-button _nb-small-button "
            + "b-dialog-upload__button-close ns-action js-hide']";
    private static final String DOWNLOAD_FILE_BUTTON_LOCATOR = "//button[@title='Скачать']";
    private static final String TRASH_LOCATOR = "//div[contains(text(), 'Корз')]";

    public void sendFile(String s) {
        waitForElementIsPresent(SEND_FILE_BUTTON_LOCATOR);
        sendFile(SEND_FILE_BUTTON_LOCATOR, PATH + s);
        waitForElementVisible(CLOSE_UPLOAD_DIALOG_BUTTON_LOCATOR);
        click(CLOSE_UPLOAD_DIALOG_BUTTON_LOCATOR);
    }

    public void downloadFile(String s) throws InterruptedException {
        String uploadFileLocator = "//div[@title='" + s + "']";
        waitForElementIsPresent(uploadFileLocator);
        click(uploadFileLocator);
        waitForElementIsPresent(DOWNLOAD_FILE_BUTTON_LOCATOR);
        click(DOWNLOAD_FILE_BUTTON_LOCATOR);
        Thread.sleep(3000);
    }

    public void moveToTrash(String s) {
        String uploadFile = "//div[@title='" + s + "']";
        waitForElementIsPresent(TRASH_LOCATOR);
        new Actions(getWrappedDriver())
                .dragAndDrop(findElement(uploadFile), findElement(TRASH_LOCATOR))
                .perform();
    }

    public void openTrash() {
        doubleClick(TRASH_LOCATOR);
    }

    public void selectFiles(String s1, String s2, String s3) {
        String file1 = "//div[@title='" + s1 + "']";
        String file2 = "//div[@title='" + s2 + "']";
        String file3 = "//div[@title='" + s3 + "']";
        new Actions(getWrappedDriver())
                .keyDown(Keys.CONTROL)
                .click(findElement(file1))
                .click(findElement(file2))
                .click(findElement(file3))
                .keyUp(Keys.CONTROL)
                .perform();
    }
}
