package yandex.page;

import ui.Browser;

public class TrashPage extends Browser {

    private static final String RESTORE_BUTTON_LOCATOR = "//button[@data-click-action='resource.restore']";
    private static final String PERMANENTLY_REMOVE_BUTTON_LOCATOR = "//button[@data-click-action='resource.delete']";
    private static final String YA_DRIVE_LOCATOR = "//*[@title='Яндекс.Диск']";
    private static final String CLEAR_TRASH_LOCATOR = "//span[contains(text(), 'Очистить Корзину')]";
    private static final String TRASH_PAGE = "https://disk.yandex.ru/client/trash";
    private static final String CONFIRM_CLEAR = "//button[@class=' nb-button _nb-small-action-button _init "
            + "b-confirmation__action b-confirmation__action_right js-confirmation-accept']";

    public void getToYaDrive() {
        click(YA_DRIVE_LOCATOR);
    }

    public void clickOnDeletedFile(String s) {
        String deletedFile = "//div[@title='" + s + "']";
        waitForElementVisible(deletedFile);
        click(deletedFile);
    }

    public void restoreFile(String s) {
        clickOnDeletedFile(s);
        waitForElementVisible(RESTORE_BUTTON_LOCATOR);
        click(RESTORE_BUTTON_LOCATOR);
    }

    public void permanentlyRemoveFile(String s) throws InterruptedException {
        clickOnDeletedFile(s);
        waitForElementVisible(PERMANENTLY_REMOVE_BUTTON_LOCATOR);
        click(PERMANENTLY_REMOVE_BUTTON_LOCATOR);
        waitForRemove(s);
    }

    public void clearTrash() {
        open(TRASH_PAGE);
        if (!isElementNotPresent(CLEAR_TRASH_LOCATOR)) {
            click(CLEAR_TRASH_LOCATOR);
            click(CONFIRM_CLEAR);
        }
    }
}
