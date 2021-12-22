package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.heap.JObject;
import com.jvm.rtda.heap.Slots;
import com.jvm.utils.ExceptionUtils;

// public final native boolean compareAndSwapObject(Object o, long offset, Object expected, Object x)
// (Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z
public class UnsafeCompareAndSwapObject implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject obj = vars.GetRef(1);
        Object fields = obj.Data();
        long offset = vars.GetLong(2);
        JObject expected = vars.GetRef(4);
        JObject newVal = vars.GetRef(5);

        if (fields instanceof Slots) {
            Slots anys = (Slots) fields;
            // object
            boolean swapped = _casObj(obj, anys, offset, expected, newVal);
            frame.OperandStack().PushBoolean(swapped);
        } else if (fields instanceof JObject[]) {
            JObject objs[] = (JObject[]) fields;
            // ref[]
            boolean swapped = _casArr(objs, offset, expected, newVal);
            frame.OperandStack().PushBoolean(swapped);
        } else {
            ExceptionUtils.throwException("todo: compareAndSwapObject!");
        }
    }


    public boolean _casObj(JObject obj, Slots fields, long offset, JObject expected, JObject newVal) {
        JObject current = fields.GetRef((int) offset);
        if (current == expected) {
            fields.SetRef((int) offset, newVal);
            return true;
        } else {
            return false;
        }
    }

    public boolean _casArr(JObject[] objs, long offset, JObject expected, JObject newVal) {
        JObject current = objs[(int) offset];
        if (current == expected) {
            objs[(int) offset] = newVal;
            return true;
        } else {
            return false;
        }
    }

}
