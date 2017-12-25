package io.dama.par.logger;

import java.io.PrintStream;

final class LoggingServiceBlockingQueue extends Thread implements Logging {
    private static LoggingServiceBlockingQueue instance = new LoggingServiceBlockingQueue();
    private static final Logging               logger   = new SimpleLoggerSingleThread();

    private LoggingServiceBlockingQueue() {
        setDaemon(true);
        setPriority(Thread.MIN_PRIORITY);
        start(); // hier kein Problem, da Klasse final
    }

    public static Logging get() {
        return LoggingServiceBlockingQueue.instance;
    }

    @Override
    public void run() {
        while (true) {
            // ...
        }
    }

    @Override
    public void log(final Severity level, final String msg) {
        // braucht nicht synchronized sein!
        switch (level) {
        case Debug:
            // ...
        case Informational:
            // ...
        case Notice:
            // ...
        case Warning:
            // ...
        case Error:
            // ...
        case Critical:
            // ...
        case Alert:
            // ...
        case Emergency:
            // ...
        }
    }

    @Override
    public synchronized void flush() {
        LoggingServiceBlockingQueue.logger.flush();
    }

    @Override
    public synchronized void setSeverityLevel(final Severity level) {
        LoggingServiceBlockingQueue.logger.setSeverityLevel(level);
    }

    @Override
    public synchronized void setPrintStream(final PrintStream out) {
        LoggingServiceBlockingQueue.logger.setPrintStream(out);
    }
}
