package com.jvm.jnative.java.lang.jclass;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JObject;
import com.jvm.rtda.heap.StringPool;


// private native String getName0();
// ()Ljava/lang/String;
//首先从局部变量表中拿到this引用，这是一个类对象引用，通 过Extra()方法可以获得与之对应的Class结构体指针。
//然后拿到类 名，转成Java字符串并推入操作数栈顶。注意这里需要的是 java.lang.Object这样的类名，而非java/lang/Object。
//Class结构体的 JavaName()方法返回转换后的类名
public class ClassGetName0 implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        JObject thiz = frame.LocalVars().GetThis();
        JClass jClass = (JClass) thiz.Extra();

        String name = jClass.JavaName();
        JObject nameObj = StringPool.JString(jClass.Loader(), name);
        frame.OperandStack().PushRef(nameObj);
    }
}
