package pp.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(final String[] args) {
        final ExecutorService executor = Executors.newCachedThreadPool();
        // Lambda-Ausdruck, mehrere Statements, explizites return
        final Future<Double> f1 = executor.submit(() -> {
            // throw new Exception("Provokation!");
            return 1.0 + 2.0;
        });
        // Callable als Inner Class
        final Future<Double> f2 = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return 3.0 + 4.0;
            }
        });
        // Lambda-Ausdruck, knapp
        final Future<Double> f3 = executor.submit(() -> f1.get() + f2.get());
        try {
            // get() blockiert, bis etwas vorliegt (auch oben)
            System.out.println(f3.get());
        } catch (InterruptedException | ExecutionException e) {
            // Exceptions in f1 und f2 werden bis zum f3.get() verzögert
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

}
