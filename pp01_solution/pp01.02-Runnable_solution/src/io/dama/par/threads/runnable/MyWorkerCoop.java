package io.dama.par.threads.runnable;

class MyWorkerCoop implements Runnable {
    private Thread self;

    @Override
    public void run() {
        this.self = Thread.currentThread();
        while (true) {
            System.out.println(this.self.getName() + ": ID => " + this.self.getId());
            Thread.yield();
        }
    }
}
