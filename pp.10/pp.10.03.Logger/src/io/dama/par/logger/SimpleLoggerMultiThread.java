package io.dama.par.logger;

import java.io.PrintStream;

public class SimpleLoggerMultiThread implements Logging, AutoCloseable {

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void log(final Severity level, final String msg) {
        // TODO Auto-generated method stub

    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSeverityLevel(final Severity level) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setPrintStream(final PrintStream out) {
        // TODO Auto-generated method stub

    }
}
