package com.jvm.jnative.java.sun.misc;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;

// private static native long handle0(int i, long l);
// (IJ)J
public class SignalHandle0 implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        vars.GetInt(0);
        vars.GetLong(1);

        OperandStack stack = frame.OperandStack();
        stack.PushLong(0l);
    }
}
