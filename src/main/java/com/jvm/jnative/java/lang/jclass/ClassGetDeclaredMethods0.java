package com.jvm.jnative.java.lang.jclass;

import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.java.lang.ClassHelper;
import com.jvm.rtda.Frame;
import com.jvm.rtda.JThread;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.*;

/*
Method(Class<?> declaringClass,
       String name,
       Class<?>[] parameterTypes,
       Class<?> returnType,
       Class<?>[] checkedExceptions,
       int modifiers,
       int slot,
       String signature,
       byte[] annotations,
       byte[] parameterAnnotations,
       byte[] annotationDefault)
*/

// private native Method[] getDeclaredMethods0(boolean publicOnly);
// (Z)[Ljava/lang/reflect/Method;
public class ClassGetDeclaredMethods0 implements JNativeMethod {
    public static final String _methodConstructorDescriptor = "" +
            "(Ljava/lang/Class;" +
            "Ljava/lang/String;" +
            "[Ljava/lang/Class;" +
            "Ljava/lang/Class;" +
            "[Ljava/lang/Class;" +
            "II" +
            "Ljava/lang/String;" +
            "[B[B[B)V";

    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject classObj = vars.GetThis();
        boolean publicOnly = vars.GetBoolean(1);

        JClass clazz = (JClass) classObj.Extra();
        JMethod methods[] = clazz.GetMethods(publicOnly);
        int methodCount = methods.length;

        JClassLoader classLoader = clazz.Loader();
        JClass methodClass = classLoader.LoadClass("java/lang/reflect/Method");
        JObject methodArr = methodClass.ArrayClass().NewArray(methodCount);

        OperandStack stack = frame.OperandStack();
        stack.PushRef(methodArr);

        // create method objs
        if (methodCount > 0) {
            JThread thread = frame.Thread();
            JObject methodObjs[] = methodArr.Refs();
            JMethod methodConstructor = methodClass.GetConstructor(_methodConstructorDescriptor);
            for (int i = 0; i < methods.length; i++) {
                JMethod method = methods[i];
                JObject methodObj = methodClass.NewObject();
                methodObj.SetExtra(method);
                methodObjs[i] = methodObj;

                OperandStack ops = new OperandStack(12);
                ops.PushRef(methodObj);                                             // this
                ops.PushRef(classObj);                                            // declaringClass
                ops.PushRef(StringPool.JString(classLoader, method.classMember.Name()));                 // name
                ops.PushRef(ClassHelper.toClassArr(classLoader, method.ParameterTypes()));         // parameterTypes
                ops.PushRef(method.ReturnType().JObject());                             // returnType
                ops.PushRef(ClassHelper.toClassArr(classLoader, method.ExceptionTypes()));       // checkedExceptions
                ops.PushInt(method.classMember.AccessFlags().Value());                              // modifiers
                ops.PushInt(0);                                             // todo: slot
                ops.PushRef(ClassHelper.getSignatureStr(classLoader, method.classMember.Signature()));        // signature
                ops.PushRef(ClassHelper.toByteArr(classLoader, method.classMember.AnnotationData()));      // annotations
                ops.PushRef(ClassHelper.toByteArr(classLoader, method.ParameterAnnotationData()));// parameterAnnotations
                ops.PushRef(ClassHelper.toByteArr(classLoader, method.AnnotationDefaultData())); // annotationDefault

                Frame shimFrame = ClassHelper.NewShimFrame(thread, ops);
                thread.PushFrame(shimFrame);

                // init methodObj
                MethodInvokeLogic.InvokeMethod(shimFrame, methodConstructor);
            }
        }
    }
}
