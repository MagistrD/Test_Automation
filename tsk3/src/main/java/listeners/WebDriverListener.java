package listeners;

import logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebDriverListener extends AbstractWebDriverEventListener {

    public static WebDriverListener create() {
        return new WebDriverListener();
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        Log.info("Browser navigate to " + url);
        super.afterNavigateTo(url, driver);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        Log.info("Before find " + by);
        super.beforeFindBy(by, element, driver);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        Log.info("Before click on " + element);
        super.beforeClickOn(element, driver);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        Log.info("Driver has exception " + throwable.toString());
        //Browser.screenshot();
        super.onException(throwable, driver);
    }
}
