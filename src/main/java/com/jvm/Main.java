package com.jvm;

import com.jvm.classfile.ClassFile;
import com.jvm.classpath.Classpath;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Main {


    public static void main(String[] args) {
        Cmd cmd = new Cmd();
        cmd.setCpOption("/Users/quyixiao/gitlab/jvm-java/target/test-classes/com/test");
        cmd.setXjreOption("/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre");
        startJVM(cmd);

    }

    public static void startJVM(Cmd cmd) {
        Classpath classpath = new Classpath(cmd.getXjreOption(), cmd.getCpOption());
        byte[] classData = classpath.readClass("CXXXX");
        System.out.println(Arrays.toString(classData));

        ClassFile classFile = ClassFile.Parse(classData);
        printClassInfo(classFile);


    }


    public static void printClassInfo(ClassFile cf) {
        log.info("version:{}.{}", cf.getMajorVersion().Value(), cf.getMinorVersion().Value());
        log.info("constants count :{}", cf.constantPool.constantInfos.length);
        log.info("access flags {} ", cf.accessFlags.Value());
        log.info("this class : {}", cf.ClassName());
        log.info("super class :{}", cf.SuperClassName());
        log.info("interfaces :{}", cf.InterfaceNames());
        log.info("fields count : {}", cf.fields.length);

        for (int i = 0; i < cf.fields.length; i++) {
            log.info(" fieldName :{}", cf.fields[i].Name());
        }
        log.info("method count :{}", cf.methods.length);
        for (int i = 0; i < cf.methods.length; i++) {
            log.info("methodName :{}", cf.methods[i].Name());
        }
    }

}
