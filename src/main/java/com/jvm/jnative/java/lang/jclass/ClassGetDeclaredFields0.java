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
Field(Class<?> declaringClass,
      String name,
      Class<?> type,
      int modifiers,
      int slot,
      String signature,
      byte[] annotations)
*/
// private native Field[] getDeclaredFields0(boolean publicOnly);
// (Z)[Ljava/lang/reflect/Field;
public class ClassGetDeclaredFields0 implements JNativeMethod {

    public final static String _fieldConstructorDescriptor = "" +
            "(Ljava/lang/Class;" +
            "Ljava/lang/String;" +
            "Ljava/lang/Class;" +
            "II" +
            "Ljava/lang/String;" +
            "[B)V";


    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject classObj = vars.GetThis();
        boolean publicOnly = vars.GetBoolean(1);

        JClass jClass = (JClass) classObj.Extra();
        JField[] fields = jClass.GetFields(publicOnly);
        int fieldCount = fields.length;

        JClassLoader classLoader = frame.Method().classMember.Class().Loader();
        JClass fieldClass = classLoader.LoadClass("java/lang/reflect/Field");
        JObject fieldArr = fieldClass.ArrayClass().NewArray(fieldCount);

        OperandStack stack = frame.OperandStack();
        stack.PushRef(fieldArr);

        if (fieldCount > 0) {
            JThread thread = frame.Thread();
            JObject fieldObjs[] = fieldArr.Refs();
            JMethod fieldConstructor = fieldClass.GetConstructor(_fieldConstructorDescriptor);
            for (int i = 0; i < fields.length; i++) {
                JField goField = fields[i];
                JObject fieldObj = fieldClass.NewObject();
                fieldObj.SetExtra(goField);
                fieldObjs[i] = fieldObj;
                OperandStack ops = new OperandStack(8);
                ops.PushRef(fieldObj);                                          // this
                ops.PushRef(classObj);                                         // declaringClass
                ops.PushRef(StringPool.JString(classLoader, goField.classMember.Name()));        // name
                ops.PushRef(goField.Type().JObject());                           // type
                ops.PushInt(goField.classMember.AccessFlags().Value());                // modifiers
                ops.PushInt(goField.SlotId());                          // slot
                ops.PushRef(ClassHelper.getSignatureStr(classLoader, goField.classMember.Signature()));// signature
                ops.PushRef(ClassHelper.toByteArr(classLoader, goField.classMember.AnnotationData()));  // annotations
                Frame shimFrame = ClassHelper.NewShimFrame(thread, ops);
                thread.PushFrame(shimFrame);
                // init fieldObj
                MethodInvokeLogic.InvokeMethod(shimFrame, fieldConstructor);
            }
        }
    }


}
