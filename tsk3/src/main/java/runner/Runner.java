package runner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import listeners.TestListener;
import logging.Log;
import org.testng.TestNG;

import java.util.Arrays;

public class Runner {

    public static TestNG configureTestNG() {
        TestNG testNG = new TestNG();
        testNG.addListener(new TestListener());
        testNG.setTestSuites(Arrays.asList(Parameters.instance().getSuitePath()));
        return testNG;
    }

    public static void main(String[] args) {
        parseCli(args);
        Log.info("Test start");
        configureTestNG().run();
        Log.info("Testing finish");
    }

    private static void parseCli(String[] args) {
        Log.info("Parse CLIs using JCommander");
        JCommander jCommander = new JCommander(Parameters.instance());
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            Log.error(e.getMessage(), e);
            System.exit(1);
        }
        if (Parameters.instance().isHelp()) {
            jCommander.usage();
            System.exit(0);
        }
    }
}
