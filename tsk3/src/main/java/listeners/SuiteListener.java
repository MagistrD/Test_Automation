package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.xml.XmlSuite;

/**
 * Created by vladl on 31.10.2016.
 */
public class SuiteListener implements ISuiteListener {
    @Override
    public void onStart(ISuite iSuite) {
        iSuite.getXmlSuite().setParallel(XmlSuite.ParallelMode.METHODS);
        iSuite.getXmlSuite().setThreadCount(3);
    }

    @Override
    public void onFinish(ISuite iSuite) {

    }
}
