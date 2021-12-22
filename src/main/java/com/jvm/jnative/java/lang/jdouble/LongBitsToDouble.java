package com.jvm.jnative.java.lang.jdouble;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.utils.ByteUtil;

// public static native double longBitsToDouble(long bits);
// (J)D
public class LongBitsToDouble implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        long bits = frame.LocalVars().GetLong(0);
        byte[] doublebits = ByteUtil.getBytes(bits);
        double d = ByteUtil.getDouble(doublebits);
        frame.OperandStack().PushDouble(d);
    }
}
