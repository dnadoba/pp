package pp.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(final String[] args) {
        final ExecutorService executor = Executors.newCachedThreadPool();
        // hier programmieren
        executor.shutdown();
    }

}
