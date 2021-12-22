package com.jvm.jnative.java.lang.jclass;

import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.java.lang.ClassHelper;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JObject;

// private native Class<?>[] getInterfaces0();
// ()[Ljava/lang/Class;
public class ClassGetInterfaces0 implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject thiz = vars.GetThis();
        JClass clazz = (JClass) thiz.Extra();
        JClass interfaces[] = clazz.Interfaces();
        JObject classArr = ClassHelper.toClassArr(clazz.Loader(), interfaces);

        OperandStack stack = frame.OperandStack();
        stack.PushRef(classArr);
    }
}
