package com.jvm.instructions.reserved;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.JRegistry;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JMethod;
import com.jvm.utils.ExceptionUtils;

// Invoke native method
public class INVOKE_NATIVE extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        JMethod method = frame.Method();
        String className = method.classMember.Class().Name();
        String methodName = method.classMember.Name();
        String methodDescriptor = method.classMember.Descriptor();
        //根据类名、方法名和方法描述符从本地方法注册表中查找本 地方法实现，如果找不到，则抛出UnsatisfiedLinkError异常
        JNativeMethod nativeMethod = JRegistry.FindNativeMethod(className, methodName, methodDescriptor);
        if (nativeMethod == null) {
            String methodInfo = className + "." + methodName + methodDescriptor;
            ExceptionUtils.throwException("java.lang.UnsatisfiedLinkError: " + methodInfo);
        }
        //否则直 接调用本地方法
        nativeMethod.run(frame);
    }
}
