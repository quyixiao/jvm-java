package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.heap.JObject;
import com.jvm.rtda.heap.Slots;
import com.jvm.utils.ExceptionUtils;


// public final native boolean compareAndSwapInt(Object o, long offset, int expected, int x);
// (Ljava/lang/Object;JII)Z
public class UnsafeCompareAndSwapInt implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        Object  fields = vars.GetRef(1).Data();
        long offset = vars.GetLong(2);
        long expected= vars.GetInt(4);
        int newVal = vars.GetInt(5);
        if(fields instanceof Slots){
            Slots slots = (Slots) fields;
            // object
            int oldVal = slots.GetInt((int)offset);
            if (oldVal == expected ){
                slots.SetInt((int)offset, newVal);
                frame.OperandStack().PushBoolean(true);
            } else {
                frame.OperandStack().PushBoolean(false);
            }
        }else if (fields instanceof  int []){
            int []ints = (int[])fields;
            // int[]
            int oldVal = ints[(int)offset];
            if( oldVal == expected ){
                ints[(int)offset] = newVal;
                frame.OperandStack().PushBoolean(true);
            } else {
                frame.OperandStack().PushBoolean(false);
            }
        }else {
            ExceptionUtils.throwException("todo: compareAndSwapInt!");
        }
    }
}
