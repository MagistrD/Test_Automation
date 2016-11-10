package runner;

import org.testng.ITestContext;
import org.testng.TestListenerAdapter;
import ui.Browser;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
        //Browser.getDriver().close();
    }
}