package io.dama.par.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class CounterTest {
	private Counter counter;
	CounterTest(Counter counter) {
		this.counter = counter;
	}
	
	void test() throws InterruptedException {
    	final int count = 100000;
    	final int threadCount = 4;
    	final int attempts = 100;
    	int summedDuration = 0;
    	for(int attempt = 0; attempt < attempts; attempt++) {
    		ArrayList<Thread> threads = new ArrayList<>();
        	for(int threadId = 0; threadId < threadCount; threadId++) {
        		Thread thread = new Thread(() -> {
        			for(int i = 0; i < count; i++) {
        				counter.increment();
        			}
        		});
        		threads.add(thread);
        	}
    		
        	for(int threadId = 0; threadId < threadCount; threadId++) {
        		Thread thread = new Thread(() -> {
        			for(int i = 0; i < count; i++) {
        				counter.decrement();
        			}
        		});
        		threads.add(thread);
        	}
    		
    		
    		long start = System.currentTimeMillis();
    		for(Thread thread: threads) {
    			thread.start();
    		}
    		for(Thread thread: threads) {
    			thread.join();
    		}
    		long end = System.currentTimeMillis();
    		long duration = end - start;
    		summedDuration += duration;
    	}
    	double duration = summedDuration / (double)attempts;
		//double durationInSeconds = duration / 1000.0;
		
		System.out.println(counter.getClass().getName() + ": " + counter.value());
		System.out.println("Duration: " + duration + "ms");
	}
	
	public static void main(String[] args) throws InterruptedException {
		CounterTest t1 = new CounterTest(new ThreadUnsafeCounter());
		CounterTest t2 = new CounterTest(new LockCounter());
		CounterTest t3 = new CounterTest(new AtomicCompareAndSetCounter());
		CounterTest t4 = new CounterTest(new AtomicIncrementAndGetCounter());
		CounterTest t5 = new CounterTest(new AtomicLazySetCounter());
		t1.test();
		t2.test();
		t3.test();
		t4.test();
		t5.test();
	}
}
