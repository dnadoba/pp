package io.dama.par.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCompareAndSetCounter implements Counter {
	private final AtomicInteger value = new AtomicInteger(0);
	@Override
	public void increment() {
		//value.getAndUpdate((i) -> i + 1);
		value.accumulateAndGet(1, (v, x) -> v + x);
	}

	@Override
	public void decrement() {
		value.accumulateAndGet(1, (v, x) -> v - x);
	}

	@Override
	public int value() {
		return value.get();
	}

	public static void main(String[] args) throws InterruptedException {
		CounterTest counterTest = new CounterTest(new AtomicCompareAndSetCounter());
		counterTest.test();
	}

}
