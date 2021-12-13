package com.jvm.instructions.math.neg;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Negate long
public class LNEG extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        long val = stack.PopLong();
        stack.PushLong(-val);
    }
}
