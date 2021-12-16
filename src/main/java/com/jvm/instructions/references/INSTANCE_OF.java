package com.jvm.instructions.references;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.ClassRef;
import com.jvm.rtda.heap.JClass;
import com.jvm.rtda.heap.JConstantPool;
import com.jvm.rtda.heap.JObject;

// Determine if object is of given type
public class INSTANCE_OF extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        JObject ref = stack.PopRef();
        if (ref == null) {
            stack.PushInt(0);
            return;
        }
        JConstantPool cp = frame.Method().classMember.Class().ConstantPool();
        ClassRef classRef = (ClassRef) cp.GetConstant(this.Index);
        JClass jClass = classRef.symRef.ResolvedClass();
        if (ref.IsInstanceOf(jClass)) {
            stack.PushInt(1);
        } else {
            stack.PushInt(0);
        }
    }
}
