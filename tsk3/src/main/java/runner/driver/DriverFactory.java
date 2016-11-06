package runner.driver;

import listeners.WebDriverListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import runner.Parameters;

import java.io.File;
import java.util.HashMap;

import static runner.driver.TimeOutsEnum.*;

public class DriverFactory {

    public static final String DOWNLOADS_PATH = "./tsk3/src/main/resources/downloads/";

    public static WebDriver chromeDriver() {
        System.setProperty("webdriver.chrome.driver", Parameters.instance().getChromeDriver());

        String downloadFilepath = DOWNLOADS_PATH;
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        WebDriver driver = new ChromeDriver(cap);

        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getTimeUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getTimeUnit());
        driver.manage().window().maximize();

        return driver;
    }

    public static WebDriver firefoxDriver() {
        FirefoxProfile fxProfile = new FirefoxProfile();
        //File file = new File(DOWNLOADS_PATH);
        File file = new File("download");
        fxProfile.setPreference("browser.download.folderList", 2);
        fxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        fxProfile.setPreference("browser.download.dir", file.getAbsolutePath());
        fxProfile.setEnableNativeEvents(true);
        fxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain, text/txt");
        WebDriver driver = new FirefoxDriver(fxProfile);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getTimeUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getTimeUnit());
        driver.manage().window().maximize();

        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(WebDriverListener.create());

        return driver;
    }
}
