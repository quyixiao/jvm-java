package com.jvm.jnative.java.lang.jobject;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ExceptionUtils;

// protected native Object clone() throws CloneNotSupportedException;
// ()Ljava/lang/Object;
//继续编辑Object.go，实现clone()方法
public class ObjectClone implements JNativeMethod {
    @Override
    public void run(Frame frame) {

        JObject thiz = frame.LocalVars().GetThis();
        //如果类没有实现Cloneable接口，则抛出CloneNotSupportedException异常
        JClass cloneable = thiz.Class().Loader().LoadClass("java/lang/Cloneable");
        if( !thiz.Class().IsImplements(cloneable) ){
            ExceptionUtils.throwException("java.lang.CloneNotSupportedException");
        }
        //否则调用Object结构体的 Clone()方法克隆对象，然后把对象副本引用推入操作数栈顶。
        frame.OperandStack().PushRef(thiz.Clone());
    }
}
