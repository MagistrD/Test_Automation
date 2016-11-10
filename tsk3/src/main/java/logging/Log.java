package logging;

import org.apache.log4j.Logger;

public class Log {
    private static final Logger LOG = Logger.getLogger("tsk3");

    public static void info(Object message) {
        LOG.info(message);
    }

    public static void error(Object message, Throwable t) {
        LOG.error(message, t);
    }

    public static void debug(Object message) {
        LOG.debug(message);
    }
}
