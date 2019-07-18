//: com.yuli.concurrency.thread.threadlocal.inheritable.ManagerThreadTest.java


package com.yuli.concurrency.thread.threadlocal.inheritable;


import com.yuli.concurrency.thread.threadlocal.UserRepository;
import org.junit.Before;
import org.junit.Test;


public class ManagerThreadTest {

    private UserRepository userRepository;
    private ManagerThread manager;

    private Thread managerThread;

    @Before
    public void setUp() throws Exception {

        this.userRepository = new UserRepository();

        this.manager = ManagerThread.builder()
                .userId(0)
                .userRepository(this.userRepository)
                .build();

        this.managerThread = new Thread(this.manager);
    }

    @Test
    public void test_Child_Thread_Context() throws Exception {

        // Given
        this.managerThread.start();

        this.managerThread.join();

        // When
        System.out.println(ManagerThread.USER_CONTEXT);
    }

}///:~