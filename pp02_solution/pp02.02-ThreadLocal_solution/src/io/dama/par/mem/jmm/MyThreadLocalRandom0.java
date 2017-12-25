package io.dama.par.mem.jmm;

import java.util.Random;

public class MyThreadLocalRandom0 implements Runnable {
    public static long now  = System.currentTimeMillis();
    public Random      rand = new Random(now);

    @Override
    public void run() {
        final StringBuffer strBuf = new StringBuffer();
        strBuf.append(Thread.currentThread().getName() + ": ");
        for (int j = 0; j < 20; j++) {
            strBuf.append(String.format("%2d ", this.rand.nextInt(100)));
        }
        System.out.println(strBuf);
    }

    public static void main(final String[] args) {
        final Runnable runner = new MyThreadLocalRandom0();
        for (int i = 0; i < 10; i++) {
            new Thread(runner, String.format("Runner-%02d", i)).start();
        }
    }
}
