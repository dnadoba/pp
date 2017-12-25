package io.dama.par.atomic;

public class LockCounter implements Counter {
	
	private int value = 0;
	@Override
	public synchronized void increment() {
		value++;
	}

	@Override
	public synchronized void decrement() {
		value--;
	}

	@Override
	public synchronized int value() {
		// TODO Auto-generated method stub
		return value;
	}

	public static void main(String[] args) throws InterruptedException {
		CounterTest counterTest = new CounterTest(new LockCounter());
		counterTest.test();
	}

}
