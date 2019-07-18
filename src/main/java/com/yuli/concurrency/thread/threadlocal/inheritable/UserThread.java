//: com.yuli.concurrency.thread.threadlocal.inheritable.UserThread.java


package com.yuli.concurrency.thread.threadlocal.inheritable;


import com.yuli.concurrency.thread.threadlocal.UserRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Builder @AllArgsConstructor
public class UserThread implements Runnable {

    int userId;
    UserRepository userRepository;

    @Override
    public void run() {
        log.info(">>>>>>> In child thread {}, the context is {}", this.userId,
                ManagerThread.USER_CONTEXT.get());
    }

}///:~