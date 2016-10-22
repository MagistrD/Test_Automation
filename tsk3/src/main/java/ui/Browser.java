package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.driver.DriverFactory;

import java.io.File;

public class Browser implements WrapsDriver {
    public static final int ELEMENT_WAIT_TIMEOUT_SECONDS = 60;
    private static String STRING_LOCATOR = "//*[contains(text(), '%s')]";

    private static WebDriver instance;
    private static WebDriver driver;
    public Browser browser;

    public Browser() {
        //System.setProperty("webdriver.chrome.driver", "tsk3/src/main/java.resources/chromedriver.exe");

        //browser = getDriver();
        setDriver(getDriver());
    }


    public static WebElement findElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }


    public static void setDriver(WebDriver driver) {
        Browser.driver = driver;
    }


    public synchronized WebDriver getDriver() {
        if (instance == null) {
            instance = DriverFactory.firefoxDriver();
        }
        return instance;
    }

    public boolean isNotPresent(String s) {
        return driver.findElements(By.xpath(String.format(STRING_LOCATOR, s))).size() == 0;
    }

    public void waitForRemove(String s) throws InterruptedException {
        for (int i = 1; i < 1000; i++) {
            if (isNotPresent(s)) {
                break;
            } else {
                Thread.sleep(1);
            }
        }
    }

//    public boolean isPresent(String s) {
//        try {
//            return Browser.getDriver().isElementPresent(By.xpath(String.format(STRING_LOCATOR, s)));
//        } catch (TimeoutException e) {
//            return false;
//        }
//    }

    //    public boolean isVisible() {
//        return Browser.getDriver().isElementVisible(by);
//    }
//
    public void waitForElementVisible(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }
//
//    public String getText() {
//        return getWrappedWebElement().getText();
//    }

    public static void click(String locator) {
        findElement(locator).click();
    }

    public void clickA(String locator) {
        new Actions(getWrappedDriver())
                .moveToElement(findElement(locator))
                .click(findElement(locator))
                .build()
                .perform();
    }

    public static void typeValue(String locator, String value) {
        //Element loginInput = new Element(By.xpath(LOGIN_INPUT_LOCATOR))
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(value);
    }


    public void sendFile(String locator, String path) {
        WebElement element = findElement(locator);
        element.sendKeys(new File(path).getAbsolutePath());
    }

    public void doubleClick(String locator) {
        new Actions(getWrappedDriver())
                .doubleClick(findElement(locator))
                .build()
                .perform();
    }

    public void close() {
        try {
            if (getWrappedDriver() != null) {
                getWrappedDriver().quit();
            }
        } finally {
            instance = null;
        }
    }

    @Override
    public WebDriver getWrappedDriver() {
        return driver;
    }

//    public void closeBrowser() {
//        driver.quit();
//    }

    public static void open(String url) {
        driver.get(url);

    }

    public void waitForElementIsPresent(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public boolean isElementPresent(String locator) {
        waitForElementIsPresent(String.format(STRING_LOCATOR, locator));
        return driver.findElements(By.xpath(String.format(STRING_LOCATOR, locator))).size() > 0;
    }


    public static boolean isElementNotPresent(String s) {
        return driver.findElements(By.xpath(s)).size() == 0;
    }
//
//    public boolean isElementVisible(By by) {
//        if (!isElementPresent(by)) {
//            return false;
//        }
//        return findElement(by).isDisplayed();
//    }
//


}
