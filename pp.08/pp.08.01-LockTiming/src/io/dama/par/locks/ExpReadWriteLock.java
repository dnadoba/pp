package io.dama.par.locks;

public class ExpReadWriteLock implements Experiment {
    private int counter;

    public ExpReadWriteLock() {
        super();
    }

    @Override
    public void incCounter() {
        this.counter++;
    }

    @Override
    public int getCounter() {
        return this.counter;
    }

    public static void main(final String[] args) throws InterruptedException {
        (new ExpReadWriteLock()).experimentPar();
    }
}
