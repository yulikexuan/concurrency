//: com.yuli.concurrency.thread.ocp8.DefiningThreadsTest.java


package com.yuli.concurrency.thread.ocp8;

import org.junit.Before;
import org.junit.Test;

//import static org.junit.Assert.*;


public class DefiningThreadsTest {

	private DefiningThreads definingThreads;

	@Before
	public void setup() {
		this.definingThreads = new DefiningThreads();
	}

	@Test
	public void test_All() {
		this.definingThreads.investigate();
	}

}///:~