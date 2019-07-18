package com.yuli.concurrency.thread.threadlocal;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class SharedMapWithUserContextTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test_run() throws Exception {

        // Given
        SharedMapWithUserContext firstUser = new SharedMapWithUserContext(1);
        SharedMapWithUserContext secondUser = new SharedMapWithUserContext(2);

        Thread t1 = new Thread(firstUser);
        Thread t2 = new Thread(secondUser);

        t1.start();
        t2.start();

        /*
         * When we invoke the join() method on a thread, the calling thread
         * goes into a waiting state. It remains in a waiting state until the
         * referenced thread terminates.
         */
        t1.join();
        t2.join();

        // When
        int mapSize = SharedMapWithUserContext.USER_CONTEXT_PER_USER_ID.size();

        // Then
        assertThat(mapSize, is(2));
    }

}