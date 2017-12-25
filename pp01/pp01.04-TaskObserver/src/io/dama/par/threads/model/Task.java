package io.dama.par.threads.model;

import io.dama.par.threads.run.Runner;

public class Task implements ITask {

    private final String id;
    private final int    nicelevel = 0;

    public Task(final String id) {
        this.id = id;
    }

    public static void main(final String[] args) {
        final ITask[] tasks = new Task[100];
        for (int i = 0; i < 100; i++) {
            tasks[i] = new Task(String.format("Task-%04d", i));
        }
        Runner.test(tasks);
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getNicelevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setNicelevel(final int nicelevel) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerObserver(final ITaskObserver o) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unregisterObserver(final ITaskObserver o) {
        // TODO Auto-generated method stub

    }

}
