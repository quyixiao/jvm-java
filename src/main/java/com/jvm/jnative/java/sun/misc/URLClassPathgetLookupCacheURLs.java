package com.jvm.jnative.java.sun.misc;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;

// private static native URL[] getLookupCacheURLs(ClassLoader var0);
// (Ljava/lang/ClassLoader;)[Ljava/net/URL;
public class URLClassPathgetLookupCacheURLs implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        frame.OperandStack().PushRef(null);
    }
}
