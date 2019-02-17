//: com.yuli.concurrency.RuntimeInterruptedException.java


package com.yuli.concurrency;


public class RuntimeInterruptedException extends RuntimeException {

	public RuntimeInterruptedException(InterruptedException cause) {
		super(cause);
	}

}///:~