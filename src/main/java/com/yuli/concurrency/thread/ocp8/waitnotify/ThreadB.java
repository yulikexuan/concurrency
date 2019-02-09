//: com.yuli.concurrency.thread.ocp8.waitnotify.ThreadB.java


package com.yuli.concurrency.thread.ocp8.waitnotify;


public class ThreadB extends Thread {

	int total;

	@Override
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 100; i++) {
				total += i;
				System.out.print(total + " ");
				if (i % 10 == 0) {
					System.out.println();
				}
			}

			/*
			 * When the wait() method is invoked on this object (in ThreadA),
			 * the thread (ThreadA) executing that code gives up its lock on
			 * the object of ThreadB immediately.
			 *
			 * However, when notify() is called here, that doesn't mean this
			 * thread gives up its lock at this moment
			 *
			 * If the thread here is still completing synchronized code,
			 * the lock is not released until the thread moves out of
			 * synchronized code.
			 *
			 * So just because notify() is called, this doesn't mean the lock
			 * becomes available at that moment.
			 */
			this.notify();

			try  {
				Thread.sleep(1000);
				System.out.println("\n>>>>>>> notify() being called here.");
				System.out.println(">>>>>>> Doesn't mean the lock becomes " +
						"available at that moment.\n");
				Thread.sleep(3000);
			} catch (InterruptedException ie) {

			}

		}
	}

}///:~