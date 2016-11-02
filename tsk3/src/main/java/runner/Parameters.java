package runner;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import ui.BrowserType;

public class Parameters {
    private static Parameters instance;

    @Parameter(names = {"--chrome", "-c"}, description = "Path to Google Chrome Driver")
    private String chromeDriver = "./tsk3/src/main/resources/chromedriver.exe";

    @Parameter(names = "--help", help = true, description = "How to use")
    private boolean help;

    @Parameter(names = {"--browser", "-b"}, description = "Browser type", converter = BrowserTypeConverter.class)
    private BrowserType browserType = BrowserType.FIREFOX;

    @Parameter(names = {"--host", "-h"}, description = "Selenium host")
    private String host = "127.0.0.1";

    @Parameter(names = {"--port", "-p"}, description = "Selenium port")
    private String port = "4444";

    @Parameter(names = {"--suite", "-st"}, description = "Suites path")
    private String suitePath = "test-suites/YandexDriveTestSuite.xml";

//    private List<String> suitePath = new ArrayList<String>();
//    {
//        //suitePath.add("test-suites/LoginTestSuite.xml");
//        suitePath.add("test-suites/YandexDriveTestSuite.xml");
//    }


    public static synchronized Parameters instance() {
        if (instance == null) {
            instance = new Parameters();
        }
        return instance;
    }

    public String getChromeDriver() {
        return chromeDriver;
    }

    public String getSuitePath() {
        return suitePath;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public boolean isHelp() {
        return help;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public static class BrowserTypeConverter implements IStringConverter<BrowserType> {
        public BrowserType convert(String s) {
            return BrowserType.valueOf(s.toUpperCase());
        }
    }
}
