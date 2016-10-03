package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorLoginPage {
    @FindBy(xpath = "//div[@class='b-login__errors']")
    private WebElement errorMsg;

    public boolean getErrorMsg() {
        return errorMsg.isDisplayed();
    }
}
