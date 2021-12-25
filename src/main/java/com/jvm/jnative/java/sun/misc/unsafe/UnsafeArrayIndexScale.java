package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// public native int arrayIndexScale(Class<?> type);
// (Ljava/lang/Class;)I
public class UnsafeArrayIndexScale implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        OperandStack stack = frame.OperandStack();
        stack.PushInt(1) ;
    }
}
