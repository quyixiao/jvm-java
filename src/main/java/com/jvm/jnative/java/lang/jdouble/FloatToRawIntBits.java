package com.jvm.jnative.java.lang.jdouble;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.utils.ByteUtil;

// public static native int floatToRawIntBits(float value);
// (F)I
public class FloatToRawIntBits implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        float value = frame.LocalVars().GetFloat(0);
        byte[] bits = ByteUtil.getBytes(value);
        frame.OperandStack().PushInt(ByteUtil.getInt(bits));
    }
}
