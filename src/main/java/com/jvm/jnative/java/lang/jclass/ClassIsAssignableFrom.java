package com.jvm.jnative.java.lang.jclass;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JObject;

// public native boolean isAssignableFrom(Class<?> cls);
// (Ljava/lang/Class;)Z
public class ClassIsAssignableFrom implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject thiz = vars.GetThis();
        JObject cls = vars.GetRef(1);

        JClass thisClass = (JClass) thiz.Extra();
        JClass clsClass = (JClass) cls.Extra();
        boolean ok = thisClass.IsAssignableFrom(clsClass);

        OperandStack stack = frame.OperandStack();
        stack.PushBoolean(ok);
    }
}
