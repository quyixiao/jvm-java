package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.java.sun.misc.Malloc;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;


// public native long reallocateMemory(long address, long bytes);
// (JJ)J
public class UnsafeReallocateMemory implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        // vars.GetRef(0) // this
        long address = vars.GetLong(1);
        long bytes = vars.GetLong(3);

        long newAddress = Malloc.reallocate(address, bytes);
        OperandStack stack = frame.OperandStack();
        stack.PushLong(newAddress);
    }
}
