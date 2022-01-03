package com.test.ch07.jvmtest;

import com.jvm.Cmd;
import com.jvm.JVM;

public class FibonacciTestTest {


    public static void main(String[] args) {
        Cmd cmd = new Cmd();
        cmd.setCpOption("/Users/quyixiao/gitlab/jvm-java/target/test-classes/com/test/ch07");
        cmd.setXjreOption("/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre");
        cmd.setJclass("FibonacciTest");
        new JVM(cmd).start();
    }
}
