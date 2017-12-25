import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class SimpleCompletableFuture {

    private static int calculate() throws Exception {
        // ...
        return 42;
    }

    static class Task implements Supplier<Integer> {

        @Override
        public Integer get() {
            return 42;
        }
    }

    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        final Future<Integer> future = CompletableFuture.supplyAsync(new Task());
        System.out.println(future.get());
    }
}
