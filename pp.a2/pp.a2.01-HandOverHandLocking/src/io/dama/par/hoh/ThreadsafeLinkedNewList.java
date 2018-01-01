package io.dama.par.hoh;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsafeLinkedNewList<T> implements NewList<T> {
    private class ListElement<T> {
        private T                    element;
        private ListElement<T>       prev;
        private final ListElement<T> next;
        private final Lock 			 lock = new ReentrantLock(); 

        private ListElement(final T element, final ListElement<T> prev, final ListElement<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private ListElement<T> first;
    private final Lock rootLock = new ReentrantLock(); 

    public ThreadsafeLinkedNewList() {
        this.first = null;
    }
    

    @Override
    public T get(final int i) {
        int j = 0;
        rootLock.lock();
        ListElement<T> ptr = this.first;
        
        Lock currentLock = ptr.lock;
        Lock nextLock;        

    	currentLock.lock();
    	rootLock.unlock();
        while (j++ < i) {
        	nextLock = ptr.next.lock;
        	nextLock.lock();
        	
            ptr = ptr.next;
            
            currentLock.unlock();
            currentLock = nextLock;
        }
        T returnValue =  ptr.element;
        currentLock.unlock();
        return returnValue;
    }

    @Override
    public void add(final T e) {
    	final ListElement<T> insert = new ListElement<>(e, null, this.first);
        rootLock.lock();
        
        if (this.first != null) {
        	this.first.lock.lock();
            this.first.prev = insert;
            this.first.lock.unlock();
        }
        this.first = insert;   
        
        rootLock.unlock();
    }

    @Override
    public void mod(final int index, final T element) {
    	if (index < 0) {
    		throw new IndexOutOfBoundsException("Index must be greater than 0");
    	}
    	
    	rootLock.lock();
    	ListElement<T> current = this.first;
    	if(current == null) {
    		throw new IndexOutOfBoundsException("Index " + index + " is out of bounds"); 
    	}
    	current.lock.lock();
    	rootLock.unlock();
        
        for (int currentIndex = 0; currentIndex < index; currentIndex++) {
        	ListElement<T> next = current.next;
        	if(next == null) {
        		throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. Max index is " + currentIndex); 
        	}
        	next.lock.lock();
        	current.lock.unlock();
            current = next;
        }
        
        current.element = element;
        current.lock.unlock();
    }

}
