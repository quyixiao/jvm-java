package com.jvm.instructions.math.rem;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Remainder double
public class DREM extends NoOperandsInstruction {

    // 求余
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        double v2 = stack.PopDouble();
        double v1 = stack.PopDouble();
        double result = v1 % v2;
        stack.PushDouble(result);
    }


}
