//: com.yuli.concurrency.thread.threadlocal.ThreadLocalWithUserContext.java


package com.yuli.concurrency.thread.threadlocal;


import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThreadLocalWithUserContext implements Runnable {

    static final ThreadLocal<Context> USER_CONTEXT = new ThreadLocal<>();

    private final Integer userId;
    private final UserRepository userRepository;

    public ThreadLocalWithUserContext(Integer userId,
                                      UserRepository userRepository) {

        this.userId = userId;
        this.userRepository = userRepository;
    }

    @Override
    public void run() {
        String userName = this.userRepository.getUserNameForUserId(this.userId);
        USER_CONTEXT.set(new Context(userName));
        log.info(">>>>>>> Thread context for given user id {} is: {}",
                this.userId, USER_CONTEXT.get());
    }

}///:~