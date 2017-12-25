package io.dama.par.mem;
public class RamTest extends Thread {
    private final int i;

    public RamTest(final int i) {
        this.i = i;
    }

    public void print(final int i) {
        final int a = i * i;
        final Integer b = new Integer(a);
        System.out.println(b);
    }

    @Override
    public void run() {
        print(this.i);
    }

    public static void main(final String[] args) {
        new Thread(new RamTest(2)).start();
        new Thread(new RamTest(3)).start();
    }
}
