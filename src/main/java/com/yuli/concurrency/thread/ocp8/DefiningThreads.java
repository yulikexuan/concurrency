//: com.yuli.concurrency.thread.ocp8.DefiningThreads.java


package com.yuli.concurrency.thread.ocp8;

import com.yuli.concurrency.IDetective;

import java.util.stream.IntStream;

public class DefiningThreads implements IDetective {

	@Override
	public void investigate() {

		/*
		 * Giving the same target to multiple threads means that several threads
		 * of execution will be running the very same job (and that the same job
		 * will be done multiple times).
		 *
		 * Thread is the worker who will do the job
		 * Runnable is the job to be done
		 */
		Runnable myRunnable = () -> {
				IntStream.range(1, 7)
						.forEach(i -> {
							System.out.println(
									i + " ----> Yu's job is running by thread "
											+ Thread.currentThread().getName());
							try {
								Thread.sleep(500);
							} catch (InterruptedException ie) {
							}
						});
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
		 * The order in which runnable threads are chosen to run is not
		 * guaranteed.
		 *
		 */
		thread_1.start();
		thread_2.start();
		thread_3.start();

		/*
		 * Once a thread has been started, it can never be started again.
		 *
		 * If you have a reference to a Thread and you call start() ,
		 * it's started. If you call start() a second time, it will cause an
		 * exception (an IllegalThreadStateException.
		 *
		 * Only a new thread can be started, and then only once.
		 * A runnable thread or a dead thread cannot be restarted.
		 */
		// thread_1.start();
	}

}///:~