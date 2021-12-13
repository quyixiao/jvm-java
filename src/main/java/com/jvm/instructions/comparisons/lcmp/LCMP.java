package com.jvm.instructions.comparisons.lcmp;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

public class LCMP extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Long v2 = stack.PopLong();
        Long v1 = stack.PopLong();
        if (v1 > v2) {
            stack.PushInt(1);
        } else if (v1.equals(v2)) {
            stack.PushInt(0);
        } else {
            stack.PushInt(-1);
        }
    }
}
