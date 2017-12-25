package pp.pool.run.cached;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    public static ExecutorService test(final Runnable task, final int tries) {
        final ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 1; i <= tries; i++) {
            pool.execute(task);
        }
        return pool;
    }

}
