package com.jvm.jnative.java.sun.io;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;

public class Win32ErrorModeSetErrorMode implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        frame.OperandStack().PushLong(0l);
    }
}
