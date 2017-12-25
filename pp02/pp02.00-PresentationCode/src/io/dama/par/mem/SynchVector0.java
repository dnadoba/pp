package io.dama.par.mem;
import java.util.Vector;

public class SynchVector0 {
    private static final int       MAX = 100;
    private static Vector<Integer> vec = new Vector<>();

    public static void main(final String[] args) throws InterruptedException {
        for (int i = 0; i < MAX; i++) {
            vec.add(i);
        }
        final Thread remover = new Thread(() -> {
            for (int i = 0; i < vec.size(); i++) {
                if ((vec.get(i) % 2) == 1) {
                    vec.remove(i);
                    try {
                        Thread.sleep(10);
                    } catch (final InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Odd-Remover");
        final Thread adder = new Thread(() -> {
            for (int i = 0; i < MAX; i++) {
                vec.add(i);
                try {
                    Thread.sleep(100);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Odd-Adder");
        remover.start();
        adder.start();
        remover.join();
        adder.join();
        for (int i = 0; i < vec.size(); i++) {
            if ((vec.get(i) % 2) == 1) {
                System.out.print(vec.get(i) + " ");
            }
        }
        System.out.println();
    }
}
