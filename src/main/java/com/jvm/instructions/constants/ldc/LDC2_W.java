package com.jvm.instructions.constants.ldc;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
import com.jvm.rtda.heap.JConstantPool;
import com.jvm.utils.ExceptionUtils;

public class LDC2_W extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        JConstantPool cp = frame.Method().classMember.Class().ConstantPool();
        Object c = cp.GetConstant(this.Index);
        if (c instanceof Long) {
            stack.PushLong((Long) c);
        } else if (c instanceof Double) {
            stack.PushDouble((Double) c);
        } else {
            ExceptionUtils.throwException("java.lang.ClassFormatError");
        }
    }
}
