package com.jvm.jnative.java.lang.jdouble;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.utils.ByteUtil;


// public static native long doubleToRawLongBits(double value);
// (D)J
public class DoubleToRawLongBits implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        double value = frame.LocalVars().GetDouble(0);
        byte[] bits = ByteUtil.getBytes(value); // todo
        frame.OperandStack().PushLong(ByteUtil.getLong(bits));
    }
}
