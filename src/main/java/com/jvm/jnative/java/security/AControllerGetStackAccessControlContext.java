package com.jvm.jnative.java.security;

import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;


// private static native AccessControlContext getStackAccessControlContext();
// ()Ljava/security/AccessControlContext;
public class AControllerGetStackAccessControlContext  implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        frame.OperandStack().PushRef(null);
    }
}
