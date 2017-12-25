package io.dama.par.threads.runnable;

public class StarterInner {
    static int WORKERS = 200;

    public static void main(final String[] args) {
        for (int i = 0; i < WORKERS; i++) {
            final Thread t = new Thread(new Runnable() {
                private Thread self;

                @Override
                public void run() {
                    this.self = Thread.currentThread();
                    while (true) {
                        System.out.println(this.self.getName() + ": ID => " + this.self.getId());
                    }
                }
            }, String.format("Worker-%03d", i));
            t.start();
        }
    }
}
