package com.jvm.jnative.java.security;

import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.jnative.JNativeMethod;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JMethod;
import com.jvm.rtda.heap.JObject;


// @CallerSensitive
// public static native <T> T
//     doPrivileged(PrivilegedExceptionAction<T> action)
//     throws PrivilegedActionException;
// (Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;

// @CallerSensitive
// public static native <T> T doPrivileged(PrivilegedAction<T> action);
// (Ljava/security/PrivilegedAction;)Ljava/lang/Object;
public class AControllerDoPrivileged implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject action = vars.GetRef(0);

        OperandStack stack = frame.OperandStack();
        stack.PushRef(action);

        JMethod method = action.Class().GetInstanceMethod("run", "()Ljava/lang/Object;"); // todo
        MethodInvokeLogic.InvokeMethod(frame, method);
    }
}
