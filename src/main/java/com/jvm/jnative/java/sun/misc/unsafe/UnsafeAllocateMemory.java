package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.java.sun.misc.Malloc;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;


// public native long allocateMemory(long bytes);
// (J)J
public class UnsafeAllocateMemory implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        // vars.GetRef(0) // this
        long bytes = vars.GetLong(1);

        long address = Malloc.allocate(bytes);
        OperandStack stack = frame.OperandStack();
        stack.PushLong(address);
    }
}
