package com.jvm.jnative.java.lang.jclass;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JClass;

// private static native boolean desiredAssertionStatus0(Class<?> clazz);
// (Ljava/lang/Class;)Z
public class ClassDesiredAssertionStatus0 implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        frame.OperandStack().PushBoolean(false);
    }
}
