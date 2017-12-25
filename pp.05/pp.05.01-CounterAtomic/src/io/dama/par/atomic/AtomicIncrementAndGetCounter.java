package io.dama.par.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIncrementAndGetCounter implements Counter {
	AtomicInteger value = new AtomicInteger();
	@Override
	public void increment() {
		value.incrementAndGet();
	}

	@Override
	public void decrement() {
		value.decrementAndGet();
	}

	@Override
	public int value() {
		return value.get();
	}
	public static void main(String[] args) throws InterruptedException {
		CounterTest counterTest = new CounterTest(new AtomicIncrementAndGetCounter());
		counterTest.test();
	}
}
