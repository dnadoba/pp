package pp.future;

public class Main {

    public static void main(final String[] args) throws InterruptedException {
        final RunnableWithResult<Integer> r1 = new RunnableWithResult1<>(() -> new Integer(1 + 2));
        final RunnableWithResult<Integer> r2 = new RunnableWithResult1<>(() -> {
            try {
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
            }
            return new Integer(3 + 4);
        });
        final Thread thread1 = new Thread(r1);
        final Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();
        while (!r1.isAvailable() || !r2.isAvailable()) {
            System.out.println("waiting on r1 or r2");
        }
        final RunnableWithResult<Integer> r3 = new RunnableWithResult1<>(() -> new Integer(r1.get() + r2.get()));
        final Thread thread3 = new Thread(r3);
        thread3.start();
        while (!r3.isAvailable()) {
            System.out.println("waiting on r3");
        }
        System.out.println("result: " + r3.get());
    }

}
