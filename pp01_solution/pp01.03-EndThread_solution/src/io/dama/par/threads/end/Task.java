package io.dama.par.threads.end;

public class Task implements Runnable {
    private volatile Thread  selfThread;
    private volatile boolean stopped = false;

    public void stopRequest() {
        this.stopped = true;
        if (this.selfThread != null) {
            this.selfThread.interrupt();
        }
    }

    public boolean isStopped() {
        return this.stopped;
    }

    @Override
    public void run() {
        this.selfThread = Thread.currentThread();
        // 1. Initialisierungsphase
        int i = 10;
        while (isStopped() == false) {
            // 2. Arbeitsphase
            System.out.println("i=" + i);
            try {
                Thread.sleep(1000 / i--);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 3. Aufräumphase
        System.out.println("fertig.");
    }
}
