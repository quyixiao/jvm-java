package com.jvm.jnative.java.lang.jobject;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JObject;

// public final native Class<?> getClass();
// ()Ljava/lang/Class;
public class ObjectGetClass implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        //首 先，从局部变量表中拿到this引用。GetThis()方法其实就是调用 GetRef(0)，不过为了提高代码的可读性，给LocalVars结构体添加了 这个方法。
        JObject thiz = frame.LocalVars().GetThis();
        //Class()方法拿到它的Class结构体指针进而又通过JClass()方法拿到它的类对象。
        JObject clazz = thiz.Class().JObject();
        //最后，把类对象推 入操作数栈顶
        frame.OperandStack().PushRef(clazz);
    }
}
