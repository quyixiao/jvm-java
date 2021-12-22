package com.jvm.jnative.java.lang;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JObject;
import com.jvm.rtda.heap.StringPool;

// public native String intern();
// ()Ljava/lang/String;
// 如果字符串还没有入池，把它放入并返回该字符串，否则找到 已入池字符串并返回
public class StringIntern implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        JObject thiz = frame.LocalVars().GetThis();
        JObject interned = StringPool.InternString(thiz);
        frame.OperandStack().PushRef(interned);
    }
}
