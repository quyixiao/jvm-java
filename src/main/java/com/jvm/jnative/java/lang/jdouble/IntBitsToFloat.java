package com.jvm.jnative.java.lang.jdouble;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.utils.ByteUtil;

// public static native float intBitsToFloat(int bits);
// (I)F
public class IntBitsToFloat implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        int bits = frame.LocalVars().GetInt(0);
        byte b[] = ByteUtil.getBytes(bits);
        frame.OperandStack().PushFloat(ByteUtil.getFloat(b));
    }
}
