package runner.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverLaunchOverviewTest {

    private static final String YANDEX_START_PAGE = "https://ya.ru";
    private WebDriver driver;

    @Test(description = "Firefox launch test")
    public void firefoxLocalLaunch() {
        driver = new FirefoxDriver();
        driver.get(YANDEX_START_PAGE);
    }

    @Test(description = "Chrome launch test")
    public void chromeLocalLaunch() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(YANDEX_START_PAGE);
    }

    @Test(description = "Firefox remote launch")
    public void firefoxRemoteLaunch() throws MalformedURLException {
        // java -jar selenium-server-standalone.jar -port 4444
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.firefox());
        driver.get(YANDEX_START_PAGE);
    }

    @Test(description = "Chrome remote launch")
    public void chromeRemoteLaunch() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
        driver.get(YANDEX_START_PAGE);
    }
}
