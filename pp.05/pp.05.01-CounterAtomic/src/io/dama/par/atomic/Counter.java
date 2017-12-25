package io.dama.par.atomic;

public interface Counter {
	public void increment();

    public void decrement();

    public int value();
}
