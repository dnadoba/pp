import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;

public class AnyOfTest {
    public static void main(final String[] args) throws InterruptedException {
        final CompletableFuture<Void> cfSleeper = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (final InterruptedException e) {
            }
        });
        final CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (final InterruptedException e) {
                }
                System.out.println("2");
            }
        });
        final CompletableFuture<Void> cf3 = CompletableFuture.runAsync(() -> {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (final InterruptedException e) {
                }
                System.out.println("3");
            }
            System.out.println("cf3 end");
        });
        CompletableFuture.anyOf(cfSleeper, cf2, cf3).join();
        cf2.cancel(true);
        try {
            cf2.join();
        } catch (final CancellationException e) {
            System.out.println("cf2 canceled");
        }
        cf3.cancel(true);
        try {
            cf3.join();
        } catch (final CancellationException e) {
            System.out.println("cf3 canceled");
        }
        Thread.sleep(5000);
    }
}
