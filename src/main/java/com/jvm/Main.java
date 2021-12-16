package com.jvm;

import com.jvm.classfile.ClassFile;
import com.jvm.classfile.MemberInfo;
import com.jvm.classpath.Classpath;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JClassLoader;
import com.jvm.rtda.heap.JMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Main {


    public static void main(String[] args) {
        Cmd cmd = new Cmd();
        cmd.setCpOption("/Users/quyixiao/gitlab/jvm-java/target/test-classes/com/test/ch06");
        cmd.setXjreOption("/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre");
        cmd.setJclass("MyObject");
        startJVM(cmd);

    }

    public static void startJVM(Cmd cmd) {
        Classpath classpath = new Classpath(cmd.getXjreOption(), cmd.getCpOption());
        byte[] data = classpath.readClass(cmd.getJclass());
        System.out.println(Arrays.toString(data));
        JClassLoader classLoader =  new JClassLoader(classpath );
        JClass mainClass = classLoader.LoadClass(cmd.getJclass());
        JMethod mainMethod = mainClass.GetMainMethod();
        if (mainMethod != null) {
            Interpreter.interpret(mainMethod);
        }
    }


    public static MemberInfo getMainMethod(ClassFile cf) {
        for (MemberInfo m : cf.Methods()) {
            if ("main".equals(m.Name()) && "([Ljava/lang/String;)V".equals(m.Descriptor())) {
                return m;
            }
        }
        return null;
    }


    public static void startJVM_4(Cmd cmd) {
        Classpath classpath = new Classpath(cmd.getXjreOption(), cmd.getCpOption());
        byte[] classData = classpath.readClass("CXXXX");
        //System.out.println(Arrays.toString(classData));
        float c = 3.1415333323232f;
        System.out.println(c);
        //ClassFile classFile = ClassFile.Parse(classData);
        //printClassInfo(classFile);
        System.out.println(Long.MAX_VALUE);
        Frame frame = new Frame(100, 100);
        testLocalVars(frame.localVars);
        testOperandStack(frame.operandStack);


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


    public static void testLocalVars(LocalVars vars) {
        vars.SetInt(0, 100);
        vars.SetInt(1, -100);
        vars.SetLong(2, 2997924580111l);
        vars.SetLong(4, -2997924580l);
        vars.SetFloat(6, 3.1415333f);
        vars.SetDouble(7, 2.718d);
        vars.SetRef(9, null);
        println(vars.GetInt(0));
        println(vars.GetInt(1));
        println(vars.GetLong(2));
        println(vars.GetLong(4));
        println(vars.GetFloat(6));
        println(vars.GetDouble(7));
        println(vars.GetRef(9));
    }

    public static void testOperandStack(OperandStack ops) {
        ops.PushInt(100);
        ops.PushInt(-100);
        ops.PushLong(2997924580l);
        ops.PushLong(-2997924580l);
        ops.PushFloat(3.1415926f);
        ops.PushDouble(2.71828182845d);
        ops.PushRef(null);
        println(ops.PopRef());
        println(ops.PopDouble());
        println(ops.PopFloat());
        println(ops.PopLong());
        println(ops.PopLong());
        println(ops.PopInt());
        println(ops.PopInt());
    }


    public static void println(Object obj) {
        System.out.println(obj);
    }


}
