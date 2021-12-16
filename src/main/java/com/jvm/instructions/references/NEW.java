package com.jvm.instructions.references;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.heap.ClassRef;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JConstantPool;
import com.jvm.rtda.heap.JObject;
import com.jvm.utils.ExceptionUtils;

public class NEW extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        JConstantPool cp = frame.Method().classMember.Class().ConstantPool();
        ClassRef classRef = (ClassRef) cp.GetConstant(this.Index);
        JClass jClass = classRef.symRef.ResolvedClass();
        // todo: init class
        if (jClass.IsInterface() || jClass.IsAbstract()) {
            ExceptionUtils.throwException("java.lang.InstantiationError");
        }

        JObject ref = jClass.NewObject();
        frame.OperandStack().PushRef(ref);
    }
}
