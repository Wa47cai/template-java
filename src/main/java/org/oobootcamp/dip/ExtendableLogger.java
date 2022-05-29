package org.oobootcamp.dip;

public class ExtendableLogger {
    public ExtendableLogger(IAppendable appender) {
        this.appender = appender;
    }

    private IAppendable appender;

    public void log(String message) {
        appender.append(message);
    }
}
