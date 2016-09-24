package com.epam.gomel.homework;

import org.testng.TestNG;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class CustomRunner {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> files = Arrays.asList(
                "./src/test/resources/suites/BoyTest.xml",
                "./src/test/resources/suites/GirlTest.xml");
        testNG.setTestSuites(files);
        testNG.run();
    }
}
