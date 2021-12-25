package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// public native int addressSize();
// ()I
public class UnsafeAddressSize implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        // vars := frame.LocalVars()
        // vars.GetRef(0) // this

        OperandStack stack = frame.OperandStack();
        stack.PushInt(8);
    }
}
