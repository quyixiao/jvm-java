package com.jvm.jnative.java.lang;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// public native int availableProcessors();
// ()I
public class RuntimeAvailableProcessors implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        int numCPU = Runtime.getRuntime().availableProcessors();

        OperandStack stack = frame.OperandStack();
        stack.PushInt(numCPU);
    }
}
