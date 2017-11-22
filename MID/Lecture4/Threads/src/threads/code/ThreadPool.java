package threads.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * With thread pool, we get a list of threads and we assign them different tasks
 * to be performed. Thread pool is usually used in the master worker design
 * pattern.
 *
 */

public class ThreadPool {
	public static void main(String[] args) {
		/**
		 * Created 2 threads, and assign tasks (Processor(i).run) to the threads
		 */
		ExecutorService executor = Executors.newFixedThreadPool(2);// 2 Threads
		for (int i = 0; i < 2; i++) { // call the (Processor(i).run) 2 times
										// with 2 threads
			executor.submit(new Processor2(i));
		}
		executor.shutdown();
		System.out.println("All tasks submitted.");
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException ignored) {
		}
		System.out.println("All tasks completed.");
	}
}

class Processor2 implements Runnable {

	private int id;

	public Processor2(int id) {
		this.id = id;
	}

	public void run() {
		System.out.println("Starting: " + id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ignored) {
		}
		System.out.println("Completed: " + id);
	}
}
