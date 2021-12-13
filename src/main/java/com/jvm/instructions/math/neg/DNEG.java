package com.jvm.instructions.math.neg;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;


// Negate double
public class DNEG extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        double val = stack.PopDouble();
        stack.PushDouble(-val);
    }
}
