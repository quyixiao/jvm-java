package com.jvm.jnative.java.lang.jobject;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JObject;


// public native int hashCode();
// ()I
public class ObjectHashCode  implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        JObject thiz = frame.LocalVars().GetThis();
        //把对象引用(Object结构体指针)转换成uintptr类型，然后强制 转换成int32推入操作数栈顶。
        frame.OperandStack().PushInt(thiz.hashCode());
    }
}
