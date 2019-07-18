package com.yuli.concurrency.thread.threadlocal;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ThreadLocalWithUserContextTest {

    private UserRepository userRepository;

    private ThreadLocalWithUserContext userContext1;
    private ThreadLocalWithUserContext userContext2;

    private Thread t1;
    private Thread t2;

    @Before
    public void setUp() throws Exception {
        this.userRepository = new UserRepository();
        this.userContext1 = new ThreadLocalWithUserContext(1,
                this.userRepository);
        this.userContext2 = new ThreadLocalWithUserContext(2,
                this.userRepository);
        this.t1 = new Thread(this.userContext1);
        this.t2 = new Thread(this.userContext2);
    }

    @Test
    public void test_run() throws Exception {

        // Given
        this.t1.start();
        this.t2.start();

        this.t1.join();
        this.t2.join();

        // When
        System.out.println(ThreadLocalWithUserContext.USER_CONTEXT);
    }
}