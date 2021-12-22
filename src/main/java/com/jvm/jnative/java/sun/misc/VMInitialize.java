package com.jvm.jnative.java.sun.misc;

import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JClassLoader;
import com.jvm.rtda.heap.JMethod;

// private static native void initialize();
// ()V
public class VMInitialize implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        JClassLoader classLoader = frame.Method().classMember.Class().Loader();
        JClass jlSysClass = classLoader.LoadClass("java/lang/System");
        JMethod initSysClass = jlSysClass.GetStaticMethod("initializeSystemClass", "()V");
        MethodInvokeLogic.InvokeMethod(frame, initSysClass);
    }
}
