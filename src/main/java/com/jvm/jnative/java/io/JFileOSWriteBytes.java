package com.jvm.jnative.java.io;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.heap.JObject;

public class JFileOSWriteBytes implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject b = vars.GetRef(1);
        int off = vars.GetInt(2);
        int len = vars.GetInt(3);

        byte[] jBytes = (byte[]) b.Data();
        byte[] newBytes = new byte[len];
        System.arraycopy(jBytes, off, newBytes, 0, len);
        System.out.println(new String(jBytes));
    }
}
