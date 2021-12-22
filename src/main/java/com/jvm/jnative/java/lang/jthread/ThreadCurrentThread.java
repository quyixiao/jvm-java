package com.jvm.jnative.java.lang.jthread;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JClassLoader;
import com.jvm.rtda.heap.JObject;

// public static native Thread currentThread();
// ()Ljava/lang/Thread;
public class ThreadCurrentThread implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        //jThread := frame.Thread().JThread()
        JClassLoader classLoader = frame.Method().classMember.Class().Loader();
        JClass threadClass = classLoader.LoadClass("java/lang/Thread");
        JObject jThread = threadClass.NewObject();

        JClass threadGroupClass = classLoader.LoadClass("java/lang/ThreadGroup");
        JObject jGroup = threadGroupClass.NewObject();

        jThread.SetRefVar("group", "Ljava/lang/ThreadGroup;", jGroup);
        jThread.SetIntVar("priority", "I", 1);

        frame.OperandStack().PushRef(jThread);
    }
}
