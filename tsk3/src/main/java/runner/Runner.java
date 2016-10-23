package runner;

import org.testng.TestNG;

import java.io.File;
import java.util.Arrays;

public class Runner {

    public static TestNG configureTestNG() {
        TestNG testNG = new TestNG();
        testNG.addListener(new TestListener());
        File loginSuite = new File("./tsk3/src/main/java/test/yandex/suites/LoginTestSuite.xml");
        File yaDiskSuite = new File("./tsk3/src/main/java/test/yandex/suites/YandexDriveTestSuite.xml");
        testNG.setTestSuites(Arrays.asList(loginSuite.getAbsolutePath(), yaDiskSuite.getAbsolutePath()));
        return testNG;
    }

    public static void main(String[] args) {
        configureTestNG().run();
    }
}
