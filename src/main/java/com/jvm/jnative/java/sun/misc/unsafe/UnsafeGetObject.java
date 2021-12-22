package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.heap.JObject;
import com.jvm.rtda.heap.Slots;
import com.jvm.utils.ExceptionUtils;

// public native Object getObject(Object o, long offset);
// (Ljava/lang/Object;J)Ljava/lang/Object;
public class UnsafeGetObject implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        Object fields = vars.GetRef(1).Data();
        long offset = vars.GetLong(2);
        if (fields instanceof Slots) {
            Slots anys = (Slots) fields;
            // object
            JObject x = anys.GetRef((int) offset);
            frame.OperandStack().PushRef(x);
        } else if (fields instanceof JObject[]) {
            JObject[] objs = (JObject[]) fields;
            // ref[]
            JObject x = objs[(int) offset];
            frame.OperandStack().PushRef(x);
        } else {
            ExceptionUtils.throwException("getObject!");
        }
    }
}
