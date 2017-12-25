package io.dama.par.locks;

public class ExpSynchronized implements Experiment {
    private int counter;

    public ExpSynchronized() {
        super();
    }

    @Override
    public synchronized void incCounter() {
        this.counter++;
    }

    @Override
    public synchronized int getCounter() {
        return this.counter;
    }

    public static void main(final String[] args) throws InterruptedException {
        (new ExpSynchronized()).experimentPar();
    }
}
