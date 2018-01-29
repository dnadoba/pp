package pp.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(final String[] args) {
        final ExecutorService executor = Executors.newCachedThreadPool();
        Callable<Integer> aCall = () -> 1 + 2;
        Future<Integer> a = executor.submit(aCall);
        Future<Integer> b = executor.submit(() -> 3 + 4);
        Future<Integer> c = executor.submit(() -> a.get() + b.get());
        try {
        	System.out.println(c.get());
        } catch(Exception e) {
        	System.out.println("error");
        } finally {
        	executor.shutdown();
		}
    }
}
