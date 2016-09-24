package com.epam.gomel.homework;

import org.testng.TestNG;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> files = Arrays.asList(
                "./src/test/resources/suite/mood.xml",
                "./src/test/resources/suite/constructors.xml");
        testNG.setTestSuites(files);
        testNG.run();
    }
}
