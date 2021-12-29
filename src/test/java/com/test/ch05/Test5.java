package com.test.ch05;

import com.jvm.Cmd;
import com.jvm.JVM;
import com.jvm.classfile.ClassFile;
import com.jvm.classpath.Classpath;

import java.util.Arrays;

public class Test5 {

    public static void main(String[] args) {
        Cmd cmd = new Cmd();
        cmd.setCpOption("/Users/quyixiao/gitlab/jvm-java/target/test-classes/com/test/ch05");
        cmd.setXjreOption("/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre");
        cmd.setJclass("GaussTest");
        new JVM(cmd).start();
    }
}
