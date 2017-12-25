package io.dama.par.locks;

public class ExpStampedLock implements Experiment {
    private int counter;

    public ExpStampedLock() {
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
        (new ExpStampedLock()).experimentPar();

    }
}
