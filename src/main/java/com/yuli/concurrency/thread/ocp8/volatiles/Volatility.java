//: com.yuli.concurrency.thread.ocp8.volatiles.Volatility.java


package com.yuli.concurrency.thread.ocp8.volatiles;


/*
 * 1. Visibility:
 *    If one thread changes a value of a variable, the change will be visible
 *    immediately to other threads reading the variable. This is guaranteed by
 *    not allowing the compiler or the JVM to allocate those variables in the
 *    CPU registers.
 *    Any write to a volatile variable is flushed immediately to main memory and
 *    any read of it is fetched from main memory.
 *    That means there is a little bit of performance penalty, but that's far
 *    better from a concurrency point of view.
 *
 * 2. Ordering: Sometimes for performance optimization, the JVM reorders
 *    instructions. This is not allowed when accessing volatile variables.
 *    Access to volatile variables is not reordered with access to other
 *    volatile variables, nor with access to other normal fields around them.
 *    This makes writes to non-volatile fields around them visible immediately
 *    to other threads.
 */
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