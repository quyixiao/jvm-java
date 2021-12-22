package com.jvm.jnative.java.util.concurrent.atomic;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;

public class AtomicLongVmSupportsCS8 implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        frame.OperandStack().PushBoolean(false);
    }
}
