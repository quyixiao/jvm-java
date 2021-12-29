package com.test.ch03;

import com.jvm.Cmd;
import com.jvm.classfile.ClassFile;
import com.jvm.classpath.Classpath;

import java.util.Arrays;

public class Test3 {


    public static void main(String[] args) {
        Cmd cmd = new Cmd();
        cmd.setCpOption("/Users/quyixiao/gitlab/jvm-java/target/test-classes/com/test/ch03");
        cmd.setXjreOption("/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre");
        cmd.setJclass("ClassFileTest");
        Classpath cp = new Classpath(cmd.getXjreOption(), cmd.getCpOption());
        byte [] b = cp.readClass(cmd.getJclass());
        System.out.println(Arrays.toString(b));
        ClassFile cf = ClassFile.Parse(b);


        System.out.println(cf);
    }

}
