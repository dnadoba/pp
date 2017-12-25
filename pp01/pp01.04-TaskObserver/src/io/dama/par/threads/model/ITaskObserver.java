package io.dama.par.threads.model;

public interface ITaskObserver {
    public void inform(ITask changed);
}
