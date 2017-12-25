import java.util.concurrent.locks.ReentrantLock;

public class LockLock {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Main start");
		Lock lock = (Lock) new ReentrantLock();
		lock.lock();
		lock.lock();
		lock.unlock();
		//lock.unlock();
		Thread thread = new Thread(() -> {
			System.out.println("aquire lock");
			lock.lock();
			System.out.println("lock aquired");
			lock.unlock();
			System.out.println("Thread end");
		});
		System.out.println("Thread start");
		thread.start();
		thread.join();
		System.out.println("Main end");
	}

}
