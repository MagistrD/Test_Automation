package test.yandex;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import runner.driver.DriverFactory;
import util.Clean;
import util.FileUtil;
import yandex.bo.Account;
import yandex.service.LoginService;
import yandex.service.TrashService;
import yandex.service.YaDiskService;
import yandex.service.YaMailService;

import java.io.File;
import java.io.IOException;

public class YandexDiskTest {

    private static final String LOGIN = "vlad.litoshik@yandex.ru";
    private static final String PASSWORD = "litoshik";
    private static final String FILES_FOLDER = "./tsk3/src/main/resources/files/";

    private FileUtil fileUtil = new FileUtil();
    private Clean clean = new Clean();
    private YaDiskService yaDiskService;
    private YaMailService yaMailService;
    private TrashService trashService;

    @BeforeMethod
    public void login() {
        Account account = new Account(LOGIN, PASSWORD);
        LoginService loginService = new LoginService();
        loginService.login(account);
        yaDiskService = new YaDiskService();
        yaMailService = new YaMailService();
    }

    @Test
    public void uploadDownloadFileTest() throws IOException, InterruptedException {
        yaMailService.getYaDisk();
        fileUtil.newFile();
        String fileName = fileUtil.getFileName();
        yaDiskService.uploadFile(fileName);
        yaDiskService.downloadFile(fileName);
        Assert.assertTrue(FileUtils.contentEquals(new File(FILES_FOLDER + fileName),
                new File(DriverFactory.DOWNLOADS_PATH + fileName)), "Upload file is not equal Download file");
    }

    @Test
    public void trashTest() throws IOException, InterruptedException {
        yaMailService.getYaDisk();
        fileUtil.newFile();
        String fileName = fileUtil.getFileName();
        yaDiskService.uploadFile(fileName);
        trashService = new TrashService();
        yaDiskService.moveFileToTrash(fileName);
        yaDiskService.openTrash();
        trashService.permanentlyRemove(fileName);
        Assert.assertTrue(trashService.isRemoved(fileName), "File is not removed");
    }

    @Test
    public void restoreTest() throws IOException, InterruptedException {
        trashService = new TrashService();
        yaMailService.getYaDisk();
        fileUtil.newFile();
        String fileName = fileUtil.getFileName();
        yaDiskService.uploadFile(fileName);
        yaDiskService.moveFileToTrash(fileName);
        yaDiskService.openTrash();
        trashService.restoreFile(fileName);
        trashService.getToYaDrive();
        Assert.assertTrue(yaDiskService.isRestore(fileName), "File is not restore");
    }

    @Test
    public void severalElementsTest() throws IOException {
        trashService = new TrashService();
        yaMailService.getYaDisk();
        fileUtil.newFile();
        String fileName1 = fileUtil.getFileName();
        yaDiskService.uploadFile(fileName1);
        fileUtil.newFile();
        String fileName2 = fileUtil.getFileName();
        yaDiskService.uploadFile(fileName2);
        fileUtil.newFile();
        String fileName3 = fileUtil.getFileName();
        yaDiskService.uploadFile(fileName3);
        yaDiskService.selectFiles(fileName1, fileName2, fileName3);
        yaDiskService.moveFileToTrash(fileName1);
        yaDiskService.openTrash();
        Assert.assertTrue(trashService.isAllFilesPresent(fileName1, fileName2, fileName3), "Excepted true");
    }

    @AfterMethod
    public void quitBrowser() throws IOException {
        //trashService.clearTrash();
        yaDiskService.closeBrowser();
        File filesFolder = new File(FILES_FOLDER);
        clean.cleanDirectory(filesFolder);
        File downloadsFolder = new File(DriverFactory.DOWNLOADS_PATH);
        clean.cleanDirectory(downloadsFolder);
    }
}
