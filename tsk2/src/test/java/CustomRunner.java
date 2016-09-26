import org.testng.TestNG;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class CustomRunner {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> files = Arrays.asList(
                ".TAT2/tsk2/src/test/resources/suites/BoyTestSuite.xml",
                "./test/resources/suites/GirlTestSuite.xml");
        testNG.setTestSuites(files);
        testNG.run();
    }
}
