package io.dama.par.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicLazySetCounter implements Counter {
	AtomicInteger value = new AtomicInteger();
	@Override
	public void increment() {
		value.lazySet(value.get() + 1);
	}

	@Override
	public void decrement() {
		value.lazySet(value.get() - 1);
	}

	@Override
	public int value() {
		return value.get();
	}
	public static void main(String[] args) throws InterruptedException {
		CounterTest counterTest = new CounterTest(new AtomicLazySetCounter());
		counterTest.test();
	}
}
