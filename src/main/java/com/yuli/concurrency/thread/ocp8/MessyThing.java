//: com.yuli.concurrency.thread.ocp8.MessyThing.java


package com.yuli.concurrency.thread.ocp8;


import com.yuli.concurrency.IDetective;


public class MessyThing implements IDetective {

	private static int staticField;

	private int nonStaticField;

	public static synchronized int getStaticField() {
		return staticField;
	}

	public static void setStaticField(int staticField) {
		MessyThing.staticField = staticField;
	}

	public synchronized int getNonStaticField() {
		return this.nonStaticField;
	}

	public synchronized void setNonStaticField(int nonStaticField) {
		this.nonStaticField = nonStaticField;
	}

	public synchronized boolean doNotDoThis() {
		return MessyThing.staticField > this.nonStaticField;
	}

	public static synchronized boolean doNotDoThisEither(
			MessyThing messyThing) {

		return MessyThing.staticField > messyThing.nonStaticField;
	}

	@Override
	public void investigate() {

	}

}///:~