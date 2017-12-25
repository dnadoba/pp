package io.dama.par.mem;
public class Counter {
    public int counter = 0;

    public static void main(final String[] args) {
        while (true) {
            final Counter c = new Counter();

            new Thread(() -> {
                int a = c.counter;
                a++;
                c.counter = a;
            }).start();

            new Thread(() -> {
                int b = c.counter;
                b += 2;
                c.counter = b;
            }).start();

            System.out.println("counter: " + c.counter);
        }
    }
}
