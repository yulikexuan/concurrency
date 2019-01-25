//: com.yuli.concurrency.thread.ocp8.NotReallyThreadSafeThing.java


package com.yuli.concurrency.thread.ocp8;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class NotReallyThreadSafeThing {

	private List<String> names = Collections.synchronizedList(new LinkedList<>());

	public void add(String name) {
		this.names.add(name);
	}

	/*
	 * Just because a class is described as "thread-safe" doesn't mean it is
	 * always thread-safe. If individual methods are synchronized, that may not
	 * be enough
	 */
	public String removeFirst() {

		if (this.names.size() > 0) {
			return this.names.remove(0);
		} else {
			return null;
		}
	}

}///:~