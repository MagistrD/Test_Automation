package mailru.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import runner.driver.TimeOutsEnum;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUpDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(TimeOutsEnum.IMPLICIT_WAIT.getValue(), TimeOutsEnum.IMPLICIT_WAIT.getTimeUnit());
        driver.manage().timeouts().pageLoadTimeout(TimeOutsEnum.PAGE_LOAD.getValue(), TimeOutsEnum.PAGE_LOAD.getTimeUnit());
    }
}
