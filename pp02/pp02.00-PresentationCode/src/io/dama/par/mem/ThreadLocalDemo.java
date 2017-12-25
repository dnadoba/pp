package io.dama.par.mem;
import java.util.Random;

public class ThreadLocalDemo {
    public static class Runner implements Runnable {
        public static ThreadLocal<Integer> mem = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return new Integer(1);
            }
        };

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep((new Random()).nextInt(2000));
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                mem.set(mem.get() + 1);
                System.out.println(Thread.currentThread().getName() + ": " + mem.get());
            }
        }
    }

    public static void main(final String[] args) {
        final Runnable runnable = new Runner();
        new Thread(runnable, "Runner-1").start();
        new Thread(runnable, "Runner-2").start();
    }
}
