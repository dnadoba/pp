package io.dama.par.boundbuffer.await;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer<T> {

    final Lock      lock     = new ReentrantLock();
    final Condition notFull  = this.lock.newCondition();
    final Condition notEmpty = this.lock.newCondition();
    final Object[]  items    = new Object[8];
    int             putptr, takeptr, count;

    public void put(final T x) throws InterruptedException {
        this.lock.lock();
        try {
            while (this.count == this.items.length) {
                this.notFull.await(); // unlock falls Bedingung
            }
            this.items[this.putptr] = x;
            if (++this.putptr == this.items.length) {
                this.putptr = 0;
            }
            ++this.count;
            this.notEmpty.signal();
        } finally {
            this.lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        this.lock.lock();
        try {
            while (this.count == 0) {
                this.notEmpty.await();
            }
            @SuppressWarnings("unchecked")
            final T x = (T) this.items[this.takeptr];
            if (++this.takeptr == this.items.length) {
                this.takeptr = 0;
            }
            --this.count;
            this.notFull.signal();
            return x;
        } finally {
            this.lock.unlock();
        }
    }

    public static void main(final String[] args) throws InterruptedException {
        final BoundedBuffer<Integer> mem = new BoundedBuffer<>();
        final Thread p1 = new Thread(() -> {
            try {
                for (int j = 1; j <= 10; j++) {
                    mem.put(new Integer(j));
                    System.out.println(Thread.currentThread().getName() + ": put=" + j);
                    Thread.sleep(1000);
                }
            } catch (final InterruptedException e) {
            }
        }, "Producer-1");
        final Thread p2 = new Thread(() -> {
            try {
                for (int j = 1; j <= 10; j++) {
                    mem.put(new Integer(j));
                    System.out.println(Thread.currentThread().getName() + ": put=" + j);
                }
            } catch (final InterruptedException e) {
            }
        }, "Producer-2");
        final Thread c1 = new Thread(() -> {
            try {
                for (int j = 1; j <= 20; j++) {
                    System.out.println(Thread.currentThread().getName() + ": taken=" + mem.take());
                }
            } catch (final InterruptedException e) {
            }
        }, "Consumer-1");
        final long now = System.currentTimeMillis();
        p1.start();
        p2.start();
        c1.start();
        p1.join();
        p2.join();
        c1.join();
        System.out.println("Runtime: " + (System.currentTimeMillis() - now) + "ms");

    }
}
