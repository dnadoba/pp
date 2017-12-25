package io.dama.par.threads.end;

import java.lang.Thread.UncaughtExceptionHandler;

public class Runner {
    public static void main(final String[] args) {
        final Task task = new Task();
        final Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                System.err.println("Unhandled Exception: " + e.getMessage());
                System.err.println(" Thread: " + t.getId() + " - " + t.getName());
                System.err.println(" Thread State: " + t.getState());
                e.printStackTrace(System.err);
            }
        });
        thread.start();
        // (new Thread(() -> thread.interrupt())).start(); // falsch: thread läuft
        // weiter
        (new Thread(() -> task.stopRequest())).start(); // richtig: thread wird
                                                        // beendet
    }
}
