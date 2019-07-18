//: com.yuli.concurrency.thread.threadlocal.UserRepository.java


package com.yuli.concurrency.thread.threadlocal;


import java.util.Map;


public class UserRepository {

    private static final Map<Integer, String> USER_INFO =
            Map.of(1, "apple", 2, "orange", 3, "banana", 4, "avocado",
                    5, "blackberry", 6, "cherry", 0, "Basket");

    public String getUserNameForUserId(int userId) {
        if (userId < 0 || userId > 6) {
            return "Unknown";
        }
        return USER_INFO.get(userId);
    }

}///:~