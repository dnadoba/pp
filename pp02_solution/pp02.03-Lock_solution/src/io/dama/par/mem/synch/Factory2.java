package io.dama.par.mem.synch;

import io.dama.par.mem.synch.model.Type;

public class Factory2 {

    private static volatile Type instance;

    public static Type getInstance() {
        Type.prepare();
        synchronized (Factory2.class) {
            if (instance == null) {
                instance = new Type();
            }
        }
        return instance;
    }

    public static void main(final String[] args) throws InterruptedException {
        final long now = System.currentTimeMillis();
        final Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                final Type object = Factory2.getInstance();
                System.out.println(Thread.currentThread().getName() + ": serial of instance = " + object.getSerial());
            }, String.format("InstanceGrabber-%02d", i));
            threads[i].start();
        }
        for (int i = 0; i < 100; i++) {
            threads[i].join();
        }
        final long time = System.currentTimeMillis() - now;
        System.out.println("Dauer: " + time + "ms");
    }

}
