//: com.yuli.concurrency.thread.ocp8.volatiles.VolatileRules.java


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
 *    This makes writes to non-volatile fields around them be visible immediately
 *    to other threads.
 *
 * Examples:
 * 1.  Because of the first rule, if thread A calls setX(), and thread B calls
 *     getX(), then the change to v will be visible immediately to thread B.
 *
 * 2.  Because of the second rule, the change to x will be visible to thread B
 *     immediately as well
 */
public class VolatileRules {

	private int x = -1;
	private volatile boolean v = false;

	public void setX(int x) {
		this.x = x;
		this.v = true;
	}

	public int getX() {
		if (this.v == true) {
			return this.x;
		}
		return 0;
	}

	/*
	 * Volatile is not suitable for some operations, like ++, --, etc.
	 * This is because these operations translate into multiple read and write
	 * instructions
	 * In a multi-threaded program, such operations should be atomic, which
	 * volatile doesn't guarantee.
	 * Java SE comes with a set of atomic classes like AtomicInteger, AtomicLong,
	 * and AtomicBoolean, which can be used to solve this problem
	 */
	public int increment() {
		//x++
		int tmp = x;
		tmp = tmp + 1;
		x = tmp;
		return x;
	}

}///:~