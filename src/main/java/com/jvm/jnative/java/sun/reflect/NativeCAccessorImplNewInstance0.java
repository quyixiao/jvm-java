package com.jvm.jnative.java.sun.reflect;

import com.jvm.instructions.base.ClassInitLogic;
import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.jnative.JNativeMethod;
import com.jvm.jnative.java.lang.ClassHelper;
import com.jvm.rtda.Frame;
import com.jvm.rtda.LocalVars;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JMethod;
import com.jvm.rtda.heap.JObject;

// private static native Object newInstance0(Constructor<?> c, Object[] os)
// throws InstantiationException, IllegalArgumentException, InvocationTargetException;
// (Ljava/lang/reflect/Constructor;[Ljava/lang/Object;)Ljava/lang/Object;
public class NativeCAccessorImplNewInstance0 implements JNativeMethod {
    @Override
    public void run(Frame frame) {
        LocalVars vars = frame.LocalVars();
        JObject constructorObj = vars.GetRef(0);
        JObject argArrObj = vars.GetRef(1);

        JMethod goConstructor = getGoConstructor(constructorObj);
        JClass goClass = goConstructor.classMember.Class();
        if (!goClass.InitStarted()) {
            frame.RevertNextPC();
            ClassInitLogic.InitClass(frame.Thread(), goClass);
            return;
        }

        JObject obj = goClass.NewObject();
        OperandStack stack = frame.OperandStack();
        stack.PushRef(obj);

        // call <init>
        OperandStack ops = convertArgs(obj, argArrObj, goConstructor);
        Frame shimFrame = ClassHelper.NewShimFrame(frame.Thread(), ops);
        frame.Thread().PushFrame(shimFrame);

        MethodInvokeLogic.InvokeMethod(shimFrame, goConstructor);
    }


    public JMethod getGoConstructor(JObject constructorObj) {
        return _getGoMethod(constructorObj, true);
    }

    public JMethod _getGoMethod(JObject methodObj, boolean isConstructor) {
        Object extra = methodObj.Extra();
        if (extra != null) {
            return (JMethod) extra;
        }

        if (isConstructor) {
            JObject root = methodObj.GetRefVar("root", "Ljava/lang/reflect/Constructor;");
            return (JMethod) root.Extra();
        } else {
            JObject root = methodObj.GetRefVar("root", "Ljava/lang/reflect/Method;");
            return (JMethod) root.Extra();
        }
    }

    // Object[] -> []interface{}
    public OperandStack convertArgs(JObject thiz, JObject argArr, JMethod method) {
        if (method.ArgSlotCount() == 0) {
            return null;
        }

        //	argObjs := argArr.Refs()
        //	argTypes := method.ParsedDescriptor().ParameterTypes()

        OperandStack ops = new OperandStack(method.ArgSlotCount());
        if (!method.classMember.IsStatic()) {
            ops.PushRef(thiz);
        }
        if (method.ArgSlotCount() == 1 && !method.classMember.IsStatic()) {
            return ops;
        }

        return ops;
    }

}
