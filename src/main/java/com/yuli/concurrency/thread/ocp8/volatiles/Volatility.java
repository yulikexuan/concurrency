//: com.yuli.concurrency.thread.ocp8.volatiles.Volatility.java


package com.yuli.concurrency.thread.ocp8.volatiles;


public class Volatility {

	// static int nextInLine = 0;
	static volatile int nextInLine = 0;

	public static void main(String... args) {

		CustomerInLine customer = new CustomerInLine(4);
		Thread customerThread = new Thread(customer);

		CustomerQueue customerQueue = new CustomerQueue();
		Thread queue = new Thread(customerQueue);

		customerThread.start();
		queue.start();
	}


}///:~