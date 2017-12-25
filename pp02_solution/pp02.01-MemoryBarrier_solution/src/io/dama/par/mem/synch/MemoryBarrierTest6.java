package io.dama.par.mem.synch;

public class MemoryBarrierTest6 extends Thread {

    public boolean stopped = false;

    @Override
    public void run() {
        while (!this.stopped) {
            // wg. Zustandswechsel
            try {
                Thread.sleep(1);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("MemoryBarrierTest-Thread actually stopped.");
    }

    public static void main(final String[] args) throws InterruptedException {
        final MemoryBarrierTest6 t = new MemoryBarrierTest6();
        t.start();
        Thread.sleep(1000);
        t.stopped = true;
        System.out.println("Main thread set stopped on MemoryBarrierTest-Thread.");
    }

}
