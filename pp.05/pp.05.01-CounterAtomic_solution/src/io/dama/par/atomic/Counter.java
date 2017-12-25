package io.dama.par.atomic;

class Counter {
    private int c = 0;

    public void increment() {
        this.c++;
    }

    public void decrement() {
        this.c--;
    }

    public int value() {
        return this.c;
    }

    public static void main(final String args[]) throws InterruptedException {
        final Counter counter = new Counter();
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
