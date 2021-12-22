package com.jvm.jnative.java.sun.reflect;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JObject;


// public static native Class<?> getCallerClass();
// ()Ljava/lang/Class;
public class ReflectionGetCallerClass implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        // top0 is sun/reflect/Reflection
        // top1 is the caller of getCallerClass()
        // top2 is the caller of method
        Frame callerFrame = frame.Thread().GetFrames()[2];// todo
        JObject callerClass = callerFrame.Method().classMember.Class().JObject();
        frame.OperandStack().PushRef(callerClass);
    }
}
