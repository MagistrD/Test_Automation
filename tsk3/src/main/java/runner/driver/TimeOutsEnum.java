package runner.driver;

import java.util.concurrent.TimeUnit;

public enum TimeOutsEnum {
    IMPLICIT_WAIT(1, TimeUnit.SECONDS),
    PAGE_LOAD(60, TimeUnit.SECONDS);

    private int value;
    private TimeUnit timeUnit;

    TimeOutsEnum(int value, TimeUnit timeUnit) {
        this.value = value;
        this.timeUnit = timeUnit;
    }

    public int getValue() {
        return value;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
