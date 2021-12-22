package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.java.sun.misc.Malloc;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.utils.ByteUtil;
import lombok.val;

//
//// public native void putShort(long address, short x);
//// (JS)V
//func mem_putShort(frame *rtda.Frame) {
//	mem, value := _put(frame)
//	PutInt16(mem, int16(value.(int32)))
//}
//
//// public native short getShort(long address);
//// (J)S
//func mem_getShort(frame *rtda.Frame) {
//	stack, mem := _get(frame)
//	stack.PushInt(int32(Int16(mem)))
//}
//
//// public native void putChar(long address, char x);
//// (JC)V
//func mem_putChar(frame *rtda.Frame) {
//	mem, value := _put(frame)
//	PutUint16(mem, uint16(value.(int32)))
//}
//
//// public native char getChar(long address);
//// (J)C
//func mem_getChar(frame *rtda.Frame) {
//	stack, mem := _get(frame)
//	stack.PushInt(int32(Uint16(mem)))
//}
//
//// public native void putInt(long address, int x);
//// (JI)V
//func mem_putInt(frame *rtda.Frame) {
//	mem, value := _put(frame)
//	PutInt32(mem, value.(int32))
//}
//
//// public native int getInt(long address);
//// (J)I
//func mem_getInt(frame *rtda.Frame) {
//	stack, mem := _get(frame)
//	stack.PushInt(Int32(mem))
//}
//
// public native void putLong(long address, long x);
// (JJ)V
public class UnsafeMem_putLong implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        // vars.GetRef(0) // this
        long address = vars.GetLong(1);
        long value = vars.GetLong(3);
        byte mem []= Malloc.memoryAt(address);
        //PutInt64(mem, value)
    }



}
