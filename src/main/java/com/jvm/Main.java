package com.jvm;

import com.jvm.classpath.Classpath;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        Cmd cmd =new Cmd();


        cmd.setCpOption(".");
        cmd.setXjreOption("/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre");
        startJVM(cmd);

    }






    public static  void startJVM(Cmd cmd){
        Classpath classpath = new Classpath(cmd.getXjreOption(),cmd.getCpOption());
        byte[] classData = classpath.readClass("java.lang.Object");
        System.out.println(Arrays.toString(classData));
    }
}
