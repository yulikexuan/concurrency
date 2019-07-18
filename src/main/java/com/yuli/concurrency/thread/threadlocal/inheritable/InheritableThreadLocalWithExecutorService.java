//: com.yuli.concurrency.thread.threadlocal.inheritable.InheritableThreadLocalWithExecutorService.java


package com.yuli.concurrency.thread.threadlocal.inheritable;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class InheritableThreadLocalWithExecutorService {

    ExecutorService executor = Executors.newSingleThreadExecutor();

    static final InheritableThreadLocal<Integer> CONTEXT =
            new InheritableThreadLocal<>();

    public void trackContext() {

        CONTEXT.set(1);
        print(CONTEXT);
        this.executor.execute(() -> print(CONTEXT));

        CONTEXT.set(2);
        print(CONTEXT);
        this.executor.execute(() -> print(CONTEXT));

        /*
         * Console output:
         *     main=1
         *     main=2
         *     pool-1-thread-1=1
         *     pool-1-thread-1=1
         *
         * What happened? The answer is quite simple.
         *   - The thread of the ExecutorService was initialized on the first
         *     call of the execute method.
         *   - Then the second call to the execute method reused the thread
         *     which was created in the first call.
         */
    }

    private void print(ThreadLocal threadLocal) {
        log.info(">>>>>>> {} = {}", Thread.currentThread().getName(), threadLocal.get());
    }

}///:~