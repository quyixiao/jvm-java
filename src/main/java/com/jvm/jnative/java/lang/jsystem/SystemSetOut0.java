package com.jvm.jnative.java.lang.jsystem;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JObject;

// private static native void setOut0(PrintStream out);
// (Ljava/io/PrintStream;)V
public class SystemSetOut0 implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject out= vars.GetRef(0);

        JClass sysClass = frame.Method().classMember.Class();
        sysClass.SetRefVar("out", "Ljava/io/PrintStream;", out);
    }
}
