//: com.yuli.concurrency.thread.ocp8.lock.ReentrantLockGuidelines.java


package com.yuli.concurrency.thread.ocp8.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockGuidelines {

	/*
	 * The constructor for this class accepts an optional fairness parameter.
	 * When set true, under contention, locks favor granting access to the
	 * longest-waiting thread.
	 *
	 * Otherwise this lock does not guarantee any particular access order.
	 *
	 * ReentrantLock class provides many methods that, as per the Javadoc,
	 * should be used only for debugging and instrumentation and not for
	 * synchronization purposes.
	 *
	 * The advice is keeping programming to the interface because you don't
	 * need to pollute your code with such granular debug information that you
	 * can actually get using a decent profiler.
	 */
	Lock lock = new ReentrantLock(); // Use this one for normal purpose.
	// Lock lock = new ReentrantLock(true);

	/*
	 * Important Usage Guidelines
	 * 1. tryLock(), unlike the rest of the methods, acts in a non-fair way
	 *    with both fair and non-fair implementations.
	 *    So, pay attention when using it with a fair lock, as you might be
	 *    expecting different behavior.
	 *
	 * 2. When using ReentrantLock, try to avoid using lock().
	 *    Use any of the other methods and, depending on the use case,
	 *    you might add an exponential backoff or maximum retries or both
	 *    methods combined.
	 *    Even if the use case requires blocking, for instance until data is
	 *    available, use the lockInterruptibly() or tryLock(long, Timeout)
	 *    methods.
	 */

	void execute() {

		boolean acquired = false;
		long wait = 100;
		int retries = 0;
		int maxRetries = 10;

		try {
			while (!acquired && retries < maxRetries) {
				acquired = lock.tryLock(wait, TimeUnit.MILLISECONDS);
				wait *= 2;
				retries++;
			}
			if (!acquired) {
				// log error or throw exception
			}
		} catch (InterruptedException e) {
			/*
			 * Log error or throw exception
			 *
			 * One bad practice, in general, is ignoring interrupts.
			 * Interrupts should be handled properly to avoid any problems with
			 * application or thread pool termination.
			 * Even when you're sure that you need to ignore them, then log the
			 * exception.
			 *
			 * But don't just swallow it.
			 */
		} finally {
			lock.unlock();
		}
	}

}///:~