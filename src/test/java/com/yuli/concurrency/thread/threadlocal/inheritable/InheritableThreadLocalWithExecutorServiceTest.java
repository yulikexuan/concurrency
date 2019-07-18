//: com.yuli.concurrency.thread.threadlocal.inheritable.InheritableThreadLocalWithExecutorServiceTest.java


package com.yuli.concurrency.thread.threadlocal.inheritable;


import org.junit.Before;
import org.junit.Test;


public class InheritableThreadLocalWithExecutorServiceTest {

    private InheritableThreadLocalWithExecutorService executorService;

    @Before
    public void setUp() throws Exception {
        this.executorService = new InheritableThreadLocalWithExecutorService();
    }

    @Test
    public void test_Track_Context() {

        // When
        this.executorService.trackContext();
    }

}///:~