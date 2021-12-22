package com.jvm.jnative.java.io;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;

public class JFileDescriptor implements JNativeMethod {


    // private static native long set(int d);
    // (I)J
    @Override
    public void run(Frame frame) {
        Class<? extends JNativeMethod> x = JFileDescriptor.class;
        frame.OperandStack().PushLong(0l);
    }
}
