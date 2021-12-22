package com.jvm.instructions.references;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.*;
import com.jvm.utils.ExceptionUtils;

// Invoke instance method;
// special handling for superclass, private, and instance initialization method invocations
public class INVOKE_SPECIAL  extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {

      JClass  currentClass = frame.Method().classMember.Class();
        JConstantPool cp = currentClass.ConstantPool();
        MethodRef methodRef = (MethodRef ) cp.GetConstant(this.Index);
        JClass resolvedClass = methodRef.memberRef.symRef.ResolvedClass();
        JMethod resolvedMethod = methodRef.ResolvedMethod();
        if (resolvedMethod.classMember.Name() == "<init>" && resolvedMethod.classMember.Class() != resolvedClass) {
            ExceptionUtils.throwException("java.lang.NoSuchMethodError");
        }
        if( resolvedMethod.classMember.IsStatic() ){
            ExceptionUtils.throwException("java.lang.IncompatibleClassChangeError");
        }

        JObject ref = frame.OperandStack().GetRefFromTop(resolvedMethod.ArgSlotCount() - 1);
        if( ref == null){
            ExceptionUtils.throwException("java.lang.NullPointerException");
        }

        if( resolvedMethod.classMember.IsProtected() &&
                resolvedMethod.classMember.Class().IsSuperClassOf(currentClass) &&
                resolvedMethod.classMember.Class().GetPackageName() != currentClass.GetPackageName() &&
                ref.Class() != currentClass &&
                !ref.Class().IsSubClassOf(currentClass) ){

            ExceptionUtils.throwException("java.lang.IllegalAccessError");
        }

        JMethod methodToBeInvoked = resolvedMethod;
        //如果调用的中超类中的函数，但不是构造函数，且当前类的ACC_SUPER标志被设置，需要一个额外的过程查找最终要调用的
        //方法;否则前面从方法符号引用中解析出来的方法就是要调用的方法。
        if( currentClass.IsSuper() &&
                resolvedClass.IsSuperClassOf(currentClass) &&
                !resolvedMethod.classMember.Name().equals("<init>")) {

            methodToBeInvoked = MethodLookup.LookupMethodInClass(currentClass.SuperClass(),
                    methodRef.memberRef.Name(), methodRef.memberRef.Descriptor());
        }

        // 如果查找过程失败，或者找到的方法是抽象的，抛出 AbstractMethodError异常。
        if (methodToBeInvoked == null || methodToBeInvoked.IsAbstract() ){
            ExceptionUtils.throwException("java.lang.AbstractMethodError");
        }
        MethodInvokeLogic.InvokeMethod(frame, methodToBeInvoked);
    }
}
