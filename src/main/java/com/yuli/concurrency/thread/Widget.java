//: com.yuli.concurrency.thread.Widget.java


package com.yuli.concurrency.thread;


public class Widget {

	public synchronized void doSomething() {
		System.out.printf(">>>>>>> %1$s super is doing something ... ...\n",
				this.getClass().getSimpleName());
	}

}///:~