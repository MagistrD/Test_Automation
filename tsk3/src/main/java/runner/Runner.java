package runner;

import org.testng.TestNG;

import java.util.Arrays;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> files = Arrays.asList(
                "./src/test/resources/suites/LoginTestSuite.xml",
                "./src/test/resources/suites/SendMailTestSuite.xml");
        testNG.setTestSuites(files);
        testNG.run();
    }
}
