package yandex.page;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import ui.Browser;

import java.io.File;
import java.io.IOException;

public class YaDiskPage extends Browser {

    private static final String SEND_FILE_BUTTON_LOCATOR = "//input[@type='file']";
    private static final String CLOSE_UPLOAD_DIALOG_BUTTON_LOCATOR = "//button[@class=' nb-button _nb-small-button "
            + "b-dialog-upload__button-close ns-action js-hide']";
    private static final String DOWNLOAD_FILE_BUTTON_LOCATOR = "//button[@title='Скачать']";
    private static final String TRASH_LOCATOR = "//div[contains(text(), 'Корз')]";

    public void sendFile(String s) {
        waitForElementIsPresent(SEND_FILE_BUTTON_LOCATOR);
        sendFile(SEND_FILE_BUTTON_LOCATOR, s);
        waitForElementVisible(CLOSE_UPLOAD_DIALOG_BUTTON_LOCATOR);
        click(CLOSE_UPLOAD_DIALOG_BUTTON_LOCATOR);
    }

    public void downloadFile(String s) throws InterruptedException, IOException {
        String uploadFileLocator = "//div[@title='" + s + "']";
        waitForElementIsPresent(uploadFileLocator);
        click(uploadFileLocator);
        waitForElementIsPresent(DOWNLOAD_FILE_BUTTON_LOCATOR);
        File file = new File("download/" + s);
        File directory = new File("download");
        directory.mkdir();
        click(DOWNLOAD_FILE_BUTTON_LOCATOR);
        while (!FileUtils.directoryContains(directory, file)) {
            Thread.sleep(1000);
        }
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
