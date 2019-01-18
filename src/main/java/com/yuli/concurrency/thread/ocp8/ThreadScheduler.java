//: com.yuli.concurrency.thread.ocp8.ThreadScheduler.java


package com.yuli.concurrency.thread.ocp8;

import java.util.stream.IntStream;

public class ThreadScheduler implements IDetective {

	@Override
	public void investigate() {

		Runnable myRunnable = () -> {
			IntStream.range(1, 7)
					.forEach(i -> {
						System.out.println(
								i + " ----> Yu's job is running by thread "
										+ Thread.currentThread().getName());
						/*
						 * What yield() is supposed to do is make the currently
						 * running thread head back to runnable to allow other
						 * threads of the same priority to get their turn.
						 *
						 * So the intention is to use yield() to promote
						 * graceful turn-taking among equal-priority threads.
						 *
						 * In reality, though, the yield() method isn't
						 * guaranteed to do what it claims, and even if yield()
						 * does cause a thread to step out of running and back
						 * to runnable, there's no guarantee the yielding thread
						 * won't just be chosen again over all the others!
						 *
						 * So while yield() might—and often does—make a running
						 * thread give up its slot to another runnable thread of
						 * the same priority, there's no guarantee.
						 *
						 * A yield() won't ever cause a thread to go to the
						 * waiting/sleeping/blocking state.
						 *
						 * At most, a yield() will cause a thread to go from
						 * running to runnable, but again, it might have no
						 * effect at all.
						 */
						Thread.yield();
					});
		};

		Thread thread_1 = new Thread(myRunnable, "T1");
		Thread thread_2 = new Thread(myRunnable, "T2");
		Thread thread_3 = new Thread(myRunnable, "T3");

		thread_1.start();
		thread_2.start();
		thread_3.start();
	}

}///:~