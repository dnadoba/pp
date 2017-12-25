package io.dama.par.atomic;

class ThreadUnsafeCounter implements Counter {
    private int c = 0;

    public void increment() {
        this.c++;
    }

    public void decrement() {
        this.c--;
    }

    public int value() {
        return this.c;
    }
    
    public static void main(String[] args) throws InterruptedException {
    	CounterTest counterTest = new CounterTest(new ThreadUnsafeCounter());
    	counterTest.test();
	}
}
