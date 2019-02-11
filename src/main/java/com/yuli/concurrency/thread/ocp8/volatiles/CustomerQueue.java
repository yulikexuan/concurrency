//: com.yuli.concurrency.thread.ocp8.volatiles.CustomerQueue.java


package com.yuli.concurrency.thread.ocp8.volatiles;


import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CustomerQueue implements Runnable {

	static final int MAX_QUEUE_LENGTH = 11;

	@Override
	public void run() {

		while (Volatility.nextInLine < MAX_QUEUE_LENGTH) {

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			log.info("-------> Processed the customer {}", Volatility.nextInLine++);
		}
	}

}///:~