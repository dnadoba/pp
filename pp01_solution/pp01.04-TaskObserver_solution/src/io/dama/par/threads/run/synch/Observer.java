package io.dama.par.threads.run.synch;

import io.dama.par.threads.model.ITask;
import io.dama.par.threads.model.ITaskObserver;

public class Observer extends Thread implements ITaskObserver {
    private final ITask task;
    private String      threadName;

    public Observer(final ITask task) {
        this.task = task;
    }

    @Override
    public void run() {
        this.threadName = Thread.currentThread().getName();
        System.out.println(this.threadName + " started monitoring " + this.task.getId());
        this.task.registerObserver(this);
        while (true) {
            Thread.yield();
        }
    }

    @Override
    public void inform(final ITask changed) {
        this.task.unregisterObserver(this);
        this.task.registerObserver(this);
        System.out.println(this.threadName + ": " + changed.getId() + " nicelevel => " + changed.getNicelevel());
    }

}
