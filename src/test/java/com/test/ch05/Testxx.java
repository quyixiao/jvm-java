package com.test.ch05;

import java.util.Properties;

public class Testxx {

    public static void main(String[] args) {
       User user = new User();
       user.userLog = new UserLog();

       UserLog b = user.userLog;
        System.out.println(b);
        user.userLog = null;
        System.out.println(b );

    }
}
