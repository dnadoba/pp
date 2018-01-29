package io.dama.par.threads.runnable;

public class StarterLambda {
    static int WORKERS = 200;

    public static void main(final String[] args) {
        for (int i = 0; i < WORKERS; i++) {
            final Thread t = new Thread(() -> {
            	Thread self = Thread.currentThread();
                while (true) {
                    System.out.println(self.getName() + ": ID => " + self.getId());
                    Thread.yield();
                }
            }, String.format("Worker-%03d", i));
            t.start();
        }
    }
}
