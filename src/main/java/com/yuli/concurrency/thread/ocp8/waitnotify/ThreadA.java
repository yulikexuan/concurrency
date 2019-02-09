//: com.yuli.concurrency.thread.ocp8.waitnotify.ThreadA.java


package com.yuli.concurrency.thread.ocp8.waitnotify;


public class ThreadA {

	public static void main(String... args) throws Exception {

		ThreadB threadB = new ThreadB();
		threadB.start();

		synchronized (threadB) {
			try {
				System.out.println("\n>>>>>>> Waiting for threadB to be completed ... ...");

				/*
				 * If this main thread does not own the lock of threadB,
				 * it will throw an IllegalMonitorStateException
				 */
				threadB.wait();
			} catch (InterruptedException ie) {
				System.out.printf("\n>>>>>>> Interrupted total is ", threadB.total);
			}
			System.out.println("\n>>>>>>> Holded the lock of threadB");
			System.out.println(">>>>>>> The final Total is " + threadB.total);
		}

	}

}///:~