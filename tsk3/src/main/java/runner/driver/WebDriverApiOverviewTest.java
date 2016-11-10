package runner.driver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WebDriverApiOverviewTest {

    private static final By SEARCH_BAR_LOCATOR = By.xpath("//input[contains(@class, 'input__control')]");
    private static final String YA_RU = "https://ya.ru";
    private static final String GOOGLE_RU = "https://google.ru";
    private static final int SHORT_SLEEP_TIMEOUT = 3000;
    private static final int IMPLICIT_WAIT_MILLIS = 100;
    private static final int IMPLICIT_WAIT_IN_MILLIS = IMPLICIT_WAIT_MILLIS;
    private static final int PAGE_LOAD_TIMEOUT_SEC = 10;
    private static final int PAGE_LOAD_TIMEOUT_IN_SEC = PAGE_LOAD_TIMEOUT_SEC;
    private static final int SCRIPT_TIMEOUT_IN_SEC = 10;
    private static final int X_POSITION = 60;
    private static final int Y_POSITION = 60;

    private WebDriver driver;

    @BeforeMethod
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void killDriver() throws InterruptedException {
        Thread.sleep(SHORT_SLEEP_TIMEOUT);
        driver.quit();
    }

    @Test
    public void driverNavigation() {
        driver.get(YA_RU);

        driver.navigate().to(GOOGLE_RU);
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        // driver.close();
        // driver.quit();
    }

    @Test
    public void driverBasicGetters() {
        driver.get(YA_RU);

        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Source: " + driver.getPageSource());
        System.out.println("Page title: " + driver.getTitle());
        System.out.println("Window handle: " + driver.getWindowHandle());
        System.out.println("Windows handles: " + driver.getWindowHandles());
    }

    @Test
    public void driverManageCookies() {
        driver.get(YA_RU);

        System.out.println("Cookies: " + driver.manage().getCookies());
    }

    @Test
    public void driverManageWindows() {
        driver.get(YA_RU);

        System.out.println("Position: " + driver.manage().window().getPosition());
        System.out.println("Size: " + driver.manage().window().getSize());
        driver.manage().window().maximize();
        System.out.println("Position: " + driver.manage().window().getPosition());
        System.out.println("Size: " + driver.manage().window().getSize());
        driver.manage().window().setPosition(new Point(X_POSITION, Y_POSITION));
        System.out.println("Position: " + driver.manage().window().getPosition());
        System.out.println("Size: " + driver.manage().window().getSize());
    }

    @Test
    public void driverManageTimeouts() {
        driver.get(YA_RU);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_IN_MILLIS, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(SCRIPT_TIMEOUT_IN_SEC, TimeUnit.SECONDS);
    }

    @Test
    public void driverManageLogs() {
        driver.get(YA_RU);
        System.out.println("Log types: " + driver.manage().logs().getAvailableLogTypes());
        System.out.println("Browser logs :");
        LogEntries logEntries = driver.manage().logs().get("browser");
        for (LogEntry logEntry : logEntries.getAll()) {
            System.out.println(logEntry);
        }
    }

    @Test
    public void driverByLocators() {
        driver.get(YA_RU);

        By.id("element-id");
        By.name("element-name");
        By.className("class-name");
        By.cssSelector("#nav > .link");
        By.tagName("button");
        By.linkText("Click me!");
        By.partialLinkText("Click");
        By.xpath("//tbody/tr[contains(@class, 'odd']");
    }

    @Test
    public void driverFindingElements() {
        driver.get(YA_RU);
        driver.findElement(SEARCH_BAR_LOCATOR).click();
        // driver.findElement(By.className("")).findElement(By.className(""));
        // driver.findElement(By.className("")).findElements(By.cssSelector(""));
    }

    @Test
    public void driverBasicElementInteractions() {
        driver.get(YA_RU);
        WebElement searchBar = driver.findElement(SEARCH_BAR_LOCATOR);
        searchBar.click();
        searchBar.sendKeys("test string");
        searchBar.clear();
        searchBar.sendKeys("test string 2");
        searchBar.submit();
    }

    @Test
    public void driverElementProperties() {
        driver.get(YA_RU);
        WebElement searchBar = driver.findElement(SEARCH_BAR_LOCATOR);
        searchBar.sendKeys("Hello");

        System.out.println("Tag: " + searchBar.getTagName());
        System.out.println("Attribute 'name': " + searchBar.getAttribute("name"));
        System.out.println("Color: " + searchBar.getCssValue("color"));
        System.out.println("Size: " + searchBar.getSize());
        System.out.println("Location: " + searchBar.getLocation());
        System.out.println("Text: " + searchBar.getText());
        System.out.println("Value: " + searchBar.getAttribute("value"));

        System.out.println("Displayed: " + searchBar.isDisplayed());
        System.out.println("Enabled: " + searchBar.isEnabled());
        System.out.println("Selected: " + searchBar.isSelected());
    }

    @Test
    public void driverSelects() {
        driver.get("http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");

        driver.switchTo().frame("iframeResult");

        // Special for select tags
        Select select = new Select(driver.findElement(By.tagName("select")));

        // Select : select smth
        select.selectByIndex(1);
        select.selectByValue("audi");
        select.selectByVisibleText("Saab");

        // Select : get options / get selected
        select.getOptions();
        select.getFirstSelectedOption();
        select.getAllSelectedOptions();

        // Select : deselect smth
        select.deselectAll();
        select.deselectByIndex(1);
        select.deselectByValue("Test");
        select.deselectByVisibleText("Test");
    }

    @Test
    public void driverAdvancedActions() {
        driver.get(YA_RU);
        WebElement searchBar = driver.findElement(SEARCH_BAR_LOCATOR);

        Actions actions = new Actions(driver).click(searchBar).keyDown(Keys.LEFT_SHIFT).sendKeys("hello");
        actions.build().perform();
        searchBar.submit();
    }

    @Test
    public void driverScript() {
        driver.get(YA_RU);
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ((JavascriptExecutor) driver).executeAsyncScript("window.open(); arguments[arguments.length - 1]()");
    }

    @Test
    public void driverSwitchTo() {
        // switch
        // driver.switchTo().window("");
        // driver.switchTo().frame(1);
        // driver.switchTo().frame("nameOrId");
        // driver.switchTo().frame(driver.findElement(By.id("")));
        // driver.switchTo().parentFrame();
        // driver.switchTo().defaultContent();
        // driver.switchTo().activeElement();

        // alert ops
        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        driver.switchTo().alert().dismiss();
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        System.out.println("Alert message: " + driver.switchTo().alert().getText());
        driver.switchTo().alert().sendKeys("Hello");
        driver.switchTo().alert().accept();

        // driver.get("http://the-internet.herokuapp.com/basic_auth");
        // driver.switchTo().alert().authenticateUsing(new
        // UserAndPassword("admin", "admin"));
    }
}
