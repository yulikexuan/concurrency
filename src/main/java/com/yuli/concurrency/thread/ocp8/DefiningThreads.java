//: com.yuli.concurrency.thread.ocp8.DefiningThreads.java


package com.yuli.concurrency.thread.ocp8;

import java.util.stream.IntStream;

public class DefiningThreads {

	public void testRunable() {

		/*
		 * Giving the same target to multiple threads means that several threads
		 * of execution will be running the very same job (and that the same job
		 * will be done multiple times).
		 *
		 * Thread is the worker who will do the job
		 * Runnable is the job to be done
		 */
		Runnable myRunnable = () -> {
				IntStream.range(1, 400)
						// .parallel()
						.forEach(i -> System.out.println(
								i + " ----> Yu's job is running by thread "
								+ Thread.currentThread().getName()));
				};

		Thread thread_1 = new Thread(myRunnable, "t1");
		Thread thread_2 = new Thread(myRunnable, "t2");
		Thread thread_3 = new Thread(myRunnable, "t3");

		/*
		 * To get an actual thread—a new call stack — we have to start the
		 * thread
		 *
		 * Start a Thread , not a Runnable .
		 * Call start() on a Thread instance, not on a Runnable instance
		 *
		 */
		thread_1.start();
		thread_2.start();
		thread_3.start();
	}

}///:~