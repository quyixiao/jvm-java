package com.test.ch08;

import com.jvm.Cmd;
import com.jvm.JVM;

public class ArrayIntDemoTest {

    public static void main(String[] args) {
        Cmd cmd = new Cmd();
        cmd.setCpOption("/Users/quyixiao/gitlab/jvm-java/target/test-classes/com/test/ch08");
        cmd.setXjreOption("/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre");
        cmd.setJclass("ArrayIntDemo");
        new JVM(cmd).start();
    }
}
