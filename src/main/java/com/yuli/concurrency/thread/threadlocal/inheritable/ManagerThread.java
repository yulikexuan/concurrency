//: com.yuli.concurrency.thread.threadlocal.inheritable.ManagerThread.java


package com.yuli.concurrency.thread.threadlocal.inheritable;


import com.yuli.concurrency.thread.threadlocal.Context;
import com.yuli.concurrency.thread.threadlocal.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Builder @AllArgsConstructor
public class ManagerThread implements Runnable {

    static final InheritableThreadLocal<Context> USER_CONTEXT =
            new InheritableThreadLocal<>() {
                @Override
                protected Context childValue(Context parentValue) {
                    return new Context("Child Context");
                }
            };

    private final int userId;
    private final UserRepository userRepository;

    @Override
    public void run() {
        USER_CONTEXT.set(new Context(this.userRepository.getUserNameForUserId(
                this.userId)));

        log.info(">>>>>>> In manager thread {}: the context is {}",
                this.userId, USER_CONTEXT.get());

        UserThread userThread = UserThread.builder()
                .userId(1)
                .userRepository(this.userRepository)
                .build();

        new Thread(userThread).start();
    }

}///:~