package pp.pool.inspect;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskLambda {

    private static final int NUMBER_OF_TASKS = 20;

    public static void main(final String[] args) {
        final ExecutorService pool1 = pp.pool.run.fixed.Runner
                .test(() -> System.out.println(Thread.currentThread().getName()), TaskLambda.NUMBER_OF_TASKS);
        final ExecutorService pool2 = pp.pool.run.cached.Runner
                .test(() -> System.out.println(Thread.currentThread().getName()), TaskLambda.NUMBER_OF_TASKS);
        final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            pool1.shutdown();
            pool2.shutdown();
            scheduler.shutdown();
        }, 5, TimeUnit.SECONDS);
    }
}
