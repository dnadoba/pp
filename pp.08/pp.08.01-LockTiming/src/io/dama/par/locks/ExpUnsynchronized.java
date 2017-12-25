package io.dama.par.locks;

public class ExpUnsynchronized implements Experiment {
    private int counter;

    public ExpUnsynchronized() {
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
        (new ExpUnsynchronized()).experimentPar();
    }
}
