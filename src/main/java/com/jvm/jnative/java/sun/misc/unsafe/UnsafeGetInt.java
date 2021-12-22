package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.Slots;
import com.jvm.utils.ExceptionUtils;

// public native boolean getInt(Object o, long offset);
// (Ljava/lang/Object;J)I
public class UnsafeGetInt implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        Object fields = vars.GetRef(1).Data();
        long offset = vars.GetLong(2);

        OperandStack stack = frame.OperandStack();
        if (fields instanceof Slots) {
            Slots slots = (Slots) fields;
            // object
            stack.PushInt(slots.GetInt((int) offset));
        } else if (fields instanceof int[]) {
            int[] shorts = (int[]) fields;
            // int[]
            stack.PushInt(shorts[(int) offset]);
        } else {
            ExceptionUtils.throwException("getInt!");
        }
    }
}
