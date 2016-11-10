package appenders;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class TestNGReportAppender extends AppenderSkeleton {
    @Override
    public boolean requiresLayout() {
        return true;
    }

    @Override
    protected void append(LoggingEvent event) {
        Reporter.log(layout.format(event));
    }

    @Override
    public void close() {
    }
}
