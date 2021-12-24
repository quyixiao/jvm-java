package com.test.ch05;

import com.jvm.Cmd;
import com.jvm.classpath.Classpath;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JClassLoader;

import java.util.Arrays;

public class TestJVM {

    public static void main(String[] args) {

        Cmd cmd = new Cmd();
        cmd.setCpOption("/Users/quyixiao/gitlab/jvm-java/target/test-classes/com/test/ch05");
        cmd.setXjreOption("/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre");
        cmd.setJclass("TestFloat");

        Classpath classpath = new Classpath(cmd.getXjreOption(), cmd.getCpOption());
        byte[] data = classpath.readClass(cmd.getJclass());
        System.out.println(Arrays.toString(data));
        JClassLoader classLoader =  new JClassLoader(classpath ,true);
        JClass mainClass = classLoader.LoadClass(cmd.getJclass());
    }
}
