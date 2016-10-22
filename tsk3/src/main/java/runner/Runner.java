package runner;

import org.testng.TestNG;

import java.util.Arrays;

public class Runner {

    public static TestNG configureTestNG() {
        TestNG testNG = new TestNG();
        testNG.addListener(new TestListener());
        testNG.setTestSuites(Arrays.asList("tsk3\\src\\main\\java\\test\\yandex\\suites\\LoginTestSuite.xml"));
        return testNG;
    }

    public static void main(String[] args) {
        configureTestNG().run();

    }
}
