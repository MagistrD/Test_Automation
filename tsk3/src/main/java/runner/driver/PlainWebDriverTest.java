package runner.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlainWebDriverTest {

    private WebDriver driver;

    @BeforeClass
    public void setupDriver() {
        driver = new FirefoxDriver();
    }

    @Test
    public void performSearchUsingYandex() {
        driver.get("https://www.ya.ru");
        driver.findElement(By.xpath("//input[contains(@class, 'input__control')]")).sendKeys("webdriver");
        driver.findElement(By.xpath("//button[@type='submit']")).submit();
        String firstLinkTitle = driver.findElement(By.xpath("(//li[@class='serp-item']//h2)[1]")).getText().trim();
        Assert.assertEquals(firstLinkTitle, "Selenium WebDriver");
    }

    @AfterClass
    public void killDriver() {
        driver.quit();
    }
}
