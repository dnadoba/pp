package io.dama.par.logger;

import java.io.PrintStream;

public interface Logging {
    public enum Severity {
        Debug, Informational, Notice, Warning, Error, Critical, Alert, Emergency;
    }

    void log(Severity level, String msg);

    void flush();

    void setSeverityLevel(Severity level);

    void setPrintStream(PrintStream out);
}
