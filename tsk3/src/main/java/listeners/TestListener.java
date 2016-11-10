package listeners;

import logging.Log;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ui.Browser;

public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info("TEST FAILED " + iTestResult.getName());
        Browser.screenshot();
        super.onTestFailure(iTestResult);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("TEST START " + iTestContext.getName());
        super.onStart(iTestContext);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        Log.info("TEST FINISH " + testContext.getName());
        super.onFinish(testContext);
    }
}