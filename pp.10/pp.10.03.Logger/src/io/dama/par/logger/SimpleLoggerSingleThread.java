package io.dama.par.logger;

import java.io.PrintStream;

public class SimpleLoggerSingleThread implements Logging, AutoCloseable {
    private static final int    CAPACITY = 1024;
    private final StringBuilder log      = new StringBuilder(SimpleLoggerSingleThread.CAPACITY);
    private Severity            level;
    private PrintStream         out;

    public SimpleLoggerSingleThread() {
        setSeverityLevel(Logging.Severity.Warning);
        setPrintStream(System.err);
    }

    public SimpleLoggerSingleThread(final Severity level) {
        this();
        setSeverityLevel(level);
    }

    public SimpleLoggerSingleThread(final Severity level, final PrintStream out) {
        this(level);
        setPrintStream(out);
    }

    @Override
    public void log(final Severity level, final String msg) {
        if (this.level.ordinal() <= level.ordinal()) {
            this.log.append(msg).append(System.lineSeparator());
            if (this.log.length() > SimpleLoggerSingleThread.CAPACITY) {
                flush();
            }
        }
    }

    @Override
    public void flush() {
        if (this.log.length() > 0) {
            this.out.print(this.log);
            this.out.flush();
            this.log.setLength(0);
        }
    }

    @Override
    public void setSeverityLevel(final Severity level) {
        this.level = level;
    }

    @Override
    public void setPrintStream(final PrintStream out) {
        this.out = out;
    }

    @Override
    public void close() throws Exception {
        flush();
    }
}
