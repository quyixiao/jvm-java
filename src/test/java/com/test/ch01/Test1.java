package com.test.ch01;

import com.jvm.Cmd;
import com.jvm.classpath.Classpath;

import java.util.Arrays;

public class Test1 {


    public static void main(String[] args) {
        Cmd cmd = new Cmd();
        cmd.setCpOption("/Users/quyixiao/gitlab/jvm-java/target/test-classes/com/test/ch06");
        cmd.setXjreOption("/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre");
        cmd.setJclass("String");
        Classpath cp = new Classpath(cmd.getXjreOption(), cmd.getCpOption());
        byte [] b = cp.readClass(cmd.getJclass());
        System.out.println(Arrays.toString(b));
    }

}
