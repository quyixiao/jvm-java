package com.jvm.jnative.java.lang.jthread;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// public final native boolean isAlive();
// ()Z
public class ThreadIsAlive implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        //vars := frame.LocalVars()
        //this := vars.GetThis()

        OperandStack stack = frame.OperandStack();
        stack.PushBoolean(false);
    }
}
