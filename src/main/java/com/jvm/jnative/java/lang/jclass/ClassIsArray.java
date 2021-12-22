package com.jvm.jnative.java.lang.jclass;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JObject;

// public native boolean isArray();
// ()Z
public class ClassIsArray implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject thiz = vars.GetThis();
        JClass clazz = (JClass) thiz.Extra();
        OperandStack stack = frame.OperandStack();
        stack.PushBoolean(clazz.IsArray());
    }
}
