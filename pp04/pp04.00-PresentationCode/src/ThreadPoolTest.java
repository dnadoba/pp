import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadPoolTest {

    public static void main(final String[] args) {
        final ExecutorService pool = Executors.newFixedThreadPool(20);
        try {
            for (int i = 0; i <= 100; i++) {
                pool.execute(() -> System.out.println(Thread.currentThread().getName()));
            }
        } finally {
            pool.shutdown();
        }
    }
}
