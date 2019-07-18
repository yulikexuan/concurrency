//: com.yuli.concurrency.thread.threadlocal.SharedMapWithUserContext.java


package com.yuli.concurrency.thread.threadlocal;


import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class SharedMapWithUserContext implements Runnable {

    public static final Map<Integer, Context> USER_CONTEXT_PER_USER_ID =
            new ConcurrentHashMap<>();

    private final Integer userId;
    private final UserRepository userRepository = new UserRepository();

    public SharedMapWithUserContext(Integer userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        String userName = this.userRepository.getUserNameForUserId(this.userId);
        USER_CONTEXT_PER_USER_ID.put(userId, new Context(userName));
        log.info(">>>>>>> Map size: {}", USER_CONTEXT_PER_USER_ID.size());
    }

}///:~