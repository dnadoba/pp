package io.dama.par.atomic;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter1 {
    private final AtomicInteger c = new AtomicInteger(0);

    public void increment() {
        int temp = this.c.get();
        while (!this.c.compareAndSet(temp, temp + 1)) {
            temp = this.c.get();
        }
    }

    public void decrement() {
        int temp = this.c.get();
        while (!this.c.compareAndSet(temp, temp - 1)) {
            temp = this.c.get();
        }
    }

    public int value() {
        return this.c.get();
    }

    public static void main(final String args[]) throws InterruptedException {
        final AtomicCounter1 counter = new AtomicCounter1();
        final Thread t1 = new Thread(() -> {
            for (int j = 0; j < 50000; j++) {
                counter.increment();
            }
        });
        final Thread t2 = new Thread(() -> {
            for (int j = 0; j < 50000; j++) {
                counter.increment();
            }
        });
        final long now = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Runtime: " + (System.currentTimeMillis() - now) + "ms");
        System.out.printf("erwartet: %s, beobachtet: %s\n", 100000, counter.value());
    }
}
