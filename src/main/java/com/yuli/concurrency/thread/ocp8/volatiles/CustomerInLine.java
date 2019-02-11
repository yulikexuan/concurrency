//: com.yuli.concurrency.thread.ocp8.volatiles.CustomerInLine.java


package com.yuli.concurrency.thread.ocp8.volatiles;


import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CustomerInLine implements Runnable {

	private final int waitNumber;

	public CustomerInLine(int waitNumber) {
		if (waitNumber > CustomerQueue.MAX_QUEUE_LENGTH) {
			throw new IllegalArgumentException("Customer is out of line.");
		}
		this.waitNumber = waitNumber;
	}

	@Override
	public void run() {

		log.info(">>>>>>> I am the {}th in line.", waitNumber);

		while (true) {
			if (Volatility.nextInLine >= waitNumber) {
				log.info(">>>>>>> Great, finally {} was called, now it is my turn.",
						waitNumber);
				break;
			}
		}
	}

}///:~