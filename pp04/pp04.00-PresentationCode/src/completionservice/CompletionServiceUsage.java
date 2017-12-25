package completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceUsage {

    public static void main(final String[] args) {
        final ExecutorService pool = Executors.newCachedThreadPool();
        final List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(() -> {
            Thread.sleep(3000);
            return "calc c1";
        });
        tasks.add(() -> {
            Thread.sleep(2000);
            return "calc c2";
        });
        tasks.add(() -> {
            Thread.sleep(1000);
            return "calc c3";
        });
        final CompletionService<String> completionService = new ExecutorCompletionService<>(pool);
        for (final Callable<String> callableTask : tasks) {
            completionService.submit(callableTask);
        }
        try {
            for (int i = 0; i < tasks.size(); i++) {
                final Future<String> future = completionService.take();
                System.out.printf("Result %2d: %s\n", i, future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
