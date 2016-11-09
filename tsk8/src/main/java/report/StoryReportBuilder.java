package report;

import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.web.selenium.WebDriverHtmlOutput;

public class StoryReportBuilder extends StoryReporterBuilder {
    public StoryReportBuilder() {
        this.withFormats(org.jbehave.core.reporters.Format.HTML, WebDriverHtmlOutput.WEB_DRIVER_HTML);
    }
}
