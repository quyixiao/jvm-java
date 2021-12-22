package com.jvm.jnative.java.sun.reflect;

import com.jvm.data.Uint16;
import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JObject;

// public static native int getClassAccessFlags(Class<?> type);
// (Ljava/lang/Class;)I
public class ReflectionGetClassAccessFlags implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject _type = vars.GetRef(0);

        JClass goClass = (JClass) _type.Extra();
        Uint16 flags = goClass.AccessFlags();
        OperandStack stack = frame.OperandStack();
        stack.PushInt(flags.Value());
    }
}
