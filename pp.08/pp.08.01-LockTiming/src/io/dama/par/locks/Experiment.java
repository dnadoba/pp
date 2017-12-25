package io.dama.par.locks;

public interface Experiment {
    final static int TRIALS = 1000;
    final static int READS  = 9999;

    default void experimentSingle() {
        int local = 0;
        for (int i = 0; i < Experiment.TRIALS; i++) {
            incCounter();
            for (int j = 0; j < Experiment.READS; j++) {
                local += getCounter();
            }
        }
    }

    default void experimentPar() throws InterruptedException {
        final Thread t1 = new Thread(() -> experimentSingle());
        final Thread t2 = new Thread(() -> experimentSingle());
        final long now = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.printf("Lauf %s, Zeitdauer: %dms",
                (getCounter() - (2 * Experiment.TRIALS)) == 0 ? "korrekt" : "fehlerhaft",
                System.currentTimeMillis() - now);
    }

    public void incCounter();

    public int getCounter();

}
