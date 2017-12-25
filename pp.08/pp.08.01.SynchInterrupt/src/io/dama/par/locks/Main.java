package io.dama.par.locks;

public class Main {
    public static void main(final String[] args) throws InterruptedException {
        final Worker w1 = new Worker();
        final Worker w2 = new Worker();
        final Thread t1 = new Thread(w1, "T1");
        final Thread t2 = new Thread(w2, "T2");
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("shutting down t2");
        w2.cancel();
        System.out.println("shutting down t1");
        w1.cancel();
        t1.join();
        t2.join();
    }
}
