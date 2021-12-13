package com.jvm.instructions.math.mul;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Multiply double
public class DMUL extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        double v2 = stack.PopDouble();
        double v1 = stack.PopDouble();
        double result = v1 * v2;
        stack.PushDouble(result);
    }
}
