package com.jvm.instructions.references;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.*;
import com.jvm.utils.ExceptionUtils;

// Invoke instance method; dispatch based on class
public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        JClass currentClass = frame.Method().classMember.Class();
        JConstantPool cp = currentClass.ConstantPool();
        MethodRef methodRef = (MethodRef) cp.GetConstant(this.Index);
        JMethod resolvedMethod = methodRef.ResolvedMethod();
        if (resolvedMethod.classMember.IsStatic()) {
            ExceptionUtils.throwException("java.lang.IncompatibleClassChangeError");
        }

        JObject ref = frame.OperandStack().GetRefFromTop(resolvedMethod.ArgSlotCount() - 1);
        if (ref == null) {
            ExceptionUtils.throwException("java.lang.NullPointerException");
        }
        if (resolvedMethod.classMember.IsProtected() &&
                resolvedMethod.classMember.Class().IsSuperClassOf(currentClass) &&
                resolvedMethod.classMember.Class().GetPackageName() != currentClass.GetPackageName() &&
                ref.Class() != currentClass &&
                !ref.Class().IsSubClassOf(currentClass)) {

            if (!(ref.Class().IsArray() && resolvedMethod.classMember.Name().equals( "clone"))) {
                ExceptionUtils.throwException("java.lang.IllegalAccessError");
            }
        }

        JMethod methodToBeInvoked=MethodLookup.LookupMethodInClass(ref.Class(), methodRef.memberRef.Name(), methodRef.memberRef.Descriptor());
        if( methodToBeInvoked == null || methodToBeInvoked.IsAbstract() ){
            ExceptionUtils.throwException("java.lang.AbstractMethodError");
        }
        //从对象的类中查找真正要调用的方法。如果找不到方法，或者 找到的是抽象方法，则需要抛出AbstractMethodError异常，否则一 切正常，调用方法。
        MethodInvokeLogic.InvokeMethod(frame, methodToBeInvoked);
    }
}
