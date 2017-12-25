package io.dama.par.locks;

public class ExpReentrantLock implements Experiment {
    private int counter;

    public ExpReentrantLock() {
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
        (new ExpReentrantLock()).experimentPar();

    }
}
