package io.dama.par.threads.run.synch;

import java.util.Random;

import io.dama.par.threads.model.ITask;

public class Runner {
    static final int MONITORS = 10;

    public static void test(final ITask[] tasks) {
        final Random r = new Random();
        for (final ITask t : tasks) {
            for (int monitors = 0; monitors < MONITORS; monitors++) {
                (new Observer(t)).start();
            }
            final Runnable changer = () -> {
                while (true) {
                    t.setNicelevel(r.nextInt(100));
                    Thread.yield();
                }
            };
            (new Thread(changer)).start();
        }
    }
}
