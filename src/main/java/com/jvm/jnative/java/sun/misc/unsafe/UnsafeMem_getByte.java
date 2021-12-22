package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.java.sun.misc.Malloc;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.utils.t.Tuple2;

//// public native void putAddress(long address, long x);
//// (JJ)V
//func putAddress(frame *rtda.Frame) {
//	mem_putLong(frame)
//}
//
//// public native long getAddress(long address);
//// (J)J
//func getAddress(frame *rtda.Frame) {
//	mem_getLong(frame)
//}
//
//// public native void putByte(long address, byte x);
//// (JB)V
//func mem_putByte(frame *rtda.Frame) {
//	mem, value := _put(frame)
//	PutInt8(mem, int8(value.(int32)))
//}
//
// public native byte getByte(long address);
// (J)B
public class UnsafeMem_getByte implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        Tuple2<OperandStack, byte[]> data = _get(frame);
        OperandStack stack = data.getFirst();
        byte[] mem = data.getSecond();
        stack.PushInt((int) Int8(mem));
    }

    public byte Int8(byte[] s) {
        return s[0];
    }


    public Tuple2<OperandStack, byte[]> _get(Frame frame) {
        LocalVars vars = frame.LocalVars();
        // vars.GetRef(0) // this
        long address = vars.GetLong(1);

        OperandStack stack = frame.OperandStack();
        byte[] mem = Malloc.memoryAt(address);
        return new Tuple2<>(stack, mem);
    }
}
