package com.jvm.instructions.references;

import com.jvm.instructions.base.ClassInitLogic;
import com.jvm.instructions.base.Index16Instruction;
import com.jvm.instructions.base.MethodInvokeLogic;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JConstantPool;
import com.jvm.rtda.heap.JMethod;
import com.jvm.rtda.heap.MethodRef;
import com.jvm.utils.ExceptionUtils;

// Invoke a class (static) method
public class INVOKE_STATIC extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        JConstantPool cp = frame.Method().classMember.Class().ConstantPool();
        MethodRef methodRef =(MethodRef) cp.GetConstant(this.Index);
        JMethod resolvedMethod = methodRef.ResolvedMethod();
        //假定解析符号引用后得到方法M。M必须是静态方法，否则抛 出Incompatible-ClassChangeError异常
        if (!resolvedMethod.classMember.IsStatic()) {
            ExceptionUtils.throwException("java.lang.IncompatibleClassChangeError");
        }
        JClass jClass = resolvedMethod.classMember.Class();
        //先判 断类的初始化是否已经开始，如果还没有，则需要调用类的初始化 方法，并终止指令执行。但是由于此时指令已经执行到了一半，
        //也 就是说当前帧的nextPC字段已经指向下一条指令，所以需要修改 nextPC，让它重新指向当前指令。Frame结构体的RevertNextPC()方 法做了这样的操作
        if (!jClass.InitStarted() ){
            frame.RevertNextPC();
            ClassInitLogic.InitClass(frame.Thread(), jClass);
            return;
        }
        MethodInvokeLogic.InvokeMethod(frame, resolvedMethod);
    }
}
