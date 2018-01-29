package io.dama.par.mem.synch;

public class MemoryBarrierTest extends Thread {

    public volatile boolean stopped = false;

    @Override
    public void run() {
        while (!this.stopped) {
            // work
        }
        System.out.println("MemoryBarrierTest-Thread actually stopped.");
    }

    public static void main(final String[] args) throws InterruptedException {
        final MemoryBarrierTest t = new MemoryBarrierTest();
        t.start();
        Thread.sleep(1000);
        t.stopped = true;
        System.out.println("Main thread set stopped on MemoryBarrierTest-Thread.");
    }

}
