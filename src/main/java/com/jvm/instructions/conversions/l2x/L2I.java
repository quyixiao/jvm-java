package com.jvm.instructions.conversions.l2x;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

public class L2I extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        long l = stack.PopLong();
        int i = (int) l;
        stack.PushInt(i);
    }
}
