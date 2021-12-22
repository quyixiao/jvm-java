package com.jvm.jnative.java.lang.jclass;

import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.java.lang.ClassHelper;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JClassLoader;
import com.jvm.rtda.heap.JMethod;
import com.jvm.rtda.heap.JObject;

/*
Constructor(Class<T> declaringClass,
            Class<?>[] parameterTypes,
            Class<?>[] checkedExceptions,
            int modifiers,
            int slot,
            String signature,
            byte[] annotations,
            byte[] parameterAnnotations)
}
*/
// private native Constructor<T>[] getDeclaredConstructors0(boolean publicOnly);
// (Z)[Ljava/lang/reflect/Constructor;
public class ClassGetDeclaredConstructors0 implements JNativeMethod {
    public static final String _constructorConstructorDescriptor = "" +
            "(Ljava/lang/Class;" +
            "[Ljava/lang/Class;" +
            "[Ljava/lang/Class;" +
            "II" +
            "Ljava/lang/String;" +
            "[B[B)V";

    @Override
    public void run(Frame frame) {

        LocalVars vars = frame.LocalVars();
        JObject classObj = vars.GetThis();
        boolean publicOnly = vars.GetBoolean(1);

        JClass jClass = (JClass) classObj.Extra();
        JMethod[] constructors = jClass.GetConstructors(publicOnly);
        int constructorCount = constructors.length;
        JClassLoader classLoader = frame.Method().classMember.Class().Loader();
        JClass constructorClass = classLoader.LoadClass("java/lang/reflect/Constructor");
        JObject constructorArr = constructorClass.ArrayClass().NewArray(constructorCount);

        OperandStack stack = frame.OperandStack();
        stack.PushRef(constructorArr);

        if (constructorCount > 0) {
            JThread thread = frame.Thread();
            JObject constructorObjs[] = constructorArr.Refs();
            JMethod constructorInitMethod = constructorClass.GetConstructor(_constructorConstructorDescriptor);
            for (int i = 0; i < constructors.length; i++) {
                JMethod constructor = constructors[i];
                JObject constructorObj = constructorClass.NewObject();
                constructorObj.SetExtra(constructor);
                constructorObjs[i] = constructorObj;

                OperandStack ops = new OperandStack(9);
                ops.PushRef(constructorObj);                                // this;
                ops.PushRef(classObj);                               // declaringClass
                ops.PushRef(ClassHelper.toClassArr(classLoader, constructor.ParameterTypes()));       // parameterTypes
                ops.PushRef(ClassHelper.toClassArr(classLoader, constructor.ExceptionTypes()));      // checkedExceptions
                ops.PushInt(constructor.classMember.AccessFlags().Value());                          // modifiers
                ops.PushInt(0);                                                  // todo slot
                ops.PushRef(ClassHelper.getSignatureStr(classLoader, constructor.classMember.Signature()));        // signature
                ops.PushRef(ClassHelper.toByteArr(classLoader, constructor.classMember.AnnotationData()));       // annotations
                ops.PushRef(ClassHelper.toByteArr(classLoader, constructor.ParameterAnnotationData())); // parameterAnnotations

                Frame shimFrame = ClassHelper.NewShimFrame(thread, ops);
                thread.PushFrame(shimFrame);
                // init constructorObj
                MethodInvokeLogic.InvokeMethod(shimFrame, constructorInitMethod);
            }
        }
    }
}
