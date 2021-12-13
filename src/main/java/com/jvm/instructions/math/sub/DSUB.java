package com.jvm.instructions.math.sub;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Subtract double
public class DSUB extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        double v2 = stack.PopDouble();
        double v1 = stack.PopDouble();
        double result = v1 - v2;
        stack.PushDouble(result);
    }
}
