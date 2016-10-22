package yandex.service;

import yandex.page.TrashPage;

public class TrashService {
    private TrashPage trashPage = new TrashPage();

    public void getToYaDrive() {
        trashPage.getToYaDrive();
    }

    public void restoreFile(String s) {
        trashPage.restoreFile(s);
    }

    public void permanentlyRemove(String s) throws InterruptedException {
        trashPage.permanentlyRemoveFile(s);
    }

    public boolean isRemoved(String s) {
        return trashPage.isNotPresent(s);
    }

    public boolean isAllFilesPresent(String s1, String s2, String s3) {
        return trashPage.isElementPresent(s1) && trashPage.isElementPresent(s2) && trashPage.isElementPresent(s3);
    }

    public void clearTrash() {
        trashPage.clearTrash();
    }

}
