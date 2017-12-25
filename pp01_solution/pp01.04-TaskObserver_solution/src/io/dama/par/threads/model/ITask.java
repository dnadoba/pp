package io.dama.par.threads.model;

public interface ITask {
    public String getId();

    public int getNicelevel();

    public void setNicelevel(int nicelevel);

    public void registerObserver(ITaskObserver o);

    public void unregisterObserver(ITaskObserver o);
}
