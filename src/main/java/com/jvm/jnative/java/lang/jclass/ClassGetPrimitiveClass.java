package com.jvm.jnative.java.lang.jclass;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JClassLoader;
import com.jvm.rtda.heap.JObject;
import com.jvm.rtda.heap.StringPool;

public class ClassGetPrimitiveClass implements JNativeMethod {

    @Override
    public void run(Frame frame) {
        JObject nameObj = frame.LocalVars().GetRef(0);
        String name = StringPool.GoString(nameObj);
        JClassLoader loader = frame.Method().classMember.Class().Loader();
        JObject jClass = loader.LoadClass(name).JObject();
        frame.OperandStack().PushRef(jClass);
    }
}
