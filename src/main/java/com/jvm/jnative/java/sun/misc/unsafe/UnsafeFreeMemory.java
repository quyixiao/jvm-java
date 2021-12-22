package com.jvm.jnative.java.sun.misc.unsafe;

import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.java.sun.misc.Malloc;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;

// public native void freeMemory(long address);
// (J)V
public class UnsafeFreeMemory implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        // vars.GetRef(0) // this
        long address = vars.GetLong(1);
        Malloc.free(address);
    }
}
