package io.dama.par.rendezvous;

import java.util.concurrent.LinkedBlockingQueue;

/*-

package main
import "fmt"
func tasker (c, r chan int, d chan bool) {
    c <- 1; c <- 2
    fmt.Println (<-r); d <- true
}
func add (c, r chan int) {
    r <- <-c + <-c
}
func main () {
    c, r:= make(chan int), make(chan int)
    done:= make(chan bool)
    go tasker (c, r, done)
    go add (c, r)
    <-done
}

 */

class Addierer {
	public static void main(String[] args) throws InterruptedException {
		LinkedBlockingQueue<Integer> c = new LinkedBlockingQueue<>();
		LinkedBlockingQueue<Integer> r = new LinkedBlockingQueue<>();
		LinkedBlockingQueue<Boolean> done = new LinkedBlockingQueue<>();
		
		Thread tasker = new Thread(() -> {
			try {
				c.put(1);
				c.put(2);
				Integer result = r.take();
				System.out.println(result);
				done.put(true);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		tasker.setDaemon(true);
		tasker.start();
		
		Thread add = new Thread(()-> {
			try {
				while(true) {
					Integer a = c.take();
					Integer b = c.take();
					r.put(a + b);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		add.setDaemon(true);
		add.start();
		
		
		done.take();
		
	}
}
