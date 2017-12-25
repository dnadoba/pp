package pp.pool.run.fixed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    private final static int SIZE = 6;

    public static ExecutorService test(final Runnable task, final int tries) {
        final ExecutorService pool = Executors.newFixedThreadPool(Runner.SIZE);
        for (int i = 1; i <= tries; i++) {
            pool.execute(task);
        }
        return pool;
    }

}
