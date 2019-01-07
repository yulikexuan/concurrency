//: com.yuli.concurrency.thread.LoggingWidget.java


package com.yuli.concurrency.thread;


public class LoggingWidget extends Widget {

	/*
	 * Intrinsic Locks
	 *     - belonging to or part of the real nature of something or someone
	 *     - Because intrinsic locks are reentrant, if a thread tries to acquire
	 *       a lock that it already holds, the request succeeds
	 */
	public synchronized void doSomething() {

		System.out.printf(">>>>>>> %1$s is calling super.doSomgthing() ... ...\n",
				this.getClass().getSimpleName());

		super.doSomething();
	}

}///:~