package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.Slot;
import com.jvm.rtda.heap.Slots;
import com.jvm.utils.ExceptionUtils;

// public final native boolean compareAndSwapLong(Object o, long offset, long expected, long x);
// (Ljava/lang/Object;JJJ)Z
public class UnsafeCompareAndSwapLong  implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        Object fields = vars.GetRef(1).Data();
        long offset = vars.GetLong(2);
        long expected = vars.GetLong(4);
        long newVal = vars.GetLong(6);

        if(fields instanceof Slots){
            Slots slots = (Slots)fields;
            // object
            long oldVal = slots.GetLong((int)offset);
            if( oldVal == expected ){
                slots.SetLong((int)offset, newVal);
                frame.OperandStack().PushBoolean(true);
            } else {
                frame.OperandStack().PushBoolean(false);
            }
        }else if (fields instanceof  long  []){
            long longs[]  = (long[]) fields;
            // long[]
            long oldVal = longs[(int)offset];
            if (oldVal == expected ){
                longs[(int)offset] = newVal;
                frame.OperandStack().PushBoolean(true);
            } else {
                frame.OperandStack().PushBoolean(false);
            }
        }else {
            ExceptionUtils.throwException("todo: compareAndSwapLong!");
        }

    }
}
