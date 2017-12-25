package io.dama.par.logger;

import java.io.PrintStream;

public class LoggingServiceThreadLocal implements Logging {
    @SuppressWarnings("resource")
    private static ThreadLocal<Logging> logger = ThreadLocal.withInitial(() -> {
        //
    });

    public static Logging get() {
        return LoggingServiceThreadLocal.logger.get();
    }

    @Override
    public void log(final Severity level, final String msg) {
        // bracuht nicht synchronized sein
        LoggingServiceThreadLocal.get().log(level, msg);
    }

    @Override
    public synchronized void flush() {
        LoggingServiceThreadLocal.get().flush();
    }

    @Override
    public synchronized void setSeverityLevel(final Severity level) {
        LoggingServiceThreadLocal.get().setSeverityLevel(level);
    }

    @Override
    public synchronized void setPrintStream(final PrintStream out) {
        LoggingServiceThreadLocal.get().setPrintStream(out);
    }
}
