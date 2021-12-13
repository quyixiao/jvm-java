package com.jvm.instructions.conversions.l2x;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;


// Convert long to double
public class L2D extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        long l = stack.PopLong();
        double d = l;
        stack.PushDouble(d);
    }
}
