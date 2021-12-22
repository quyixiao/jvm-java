package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JObject;
// public native long objectFieldOffset(Field field);
// (Ljava/lang/reflect/Field;)J
public class UnsafeObjectFieldOffset implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject jField = vars.GetRef(1);

        int offset = jField.GetIntVar("slot", "I");

        OperandStack stack = frame.OperandStack();
        stack.PushLong((long) offset);
    }
}
