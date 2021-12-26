package com.jvm.jnative.java.sun.misc;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;

import javax.naming.OperationNotSupportedException;

// private static native int findSignal(String string);
// (Ljava/lang/String;)I
public class SignalFindSignal implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        vars.GetRef(0) ;
        OperandStack stack = frame.OperandStack();
        stack.PushInt(0);
    }
}
