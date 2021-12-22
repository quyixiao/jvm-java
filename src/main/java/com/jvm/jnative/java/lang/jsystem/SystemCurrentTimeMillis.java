package com.jvm.jnative.java.lang.jsystem;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

public class SystemCurrentTimeMillis implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        OperandStack stack = frame.OperandStack();
        stack.PushLong(System.currentTimeMillis());
    }
}
