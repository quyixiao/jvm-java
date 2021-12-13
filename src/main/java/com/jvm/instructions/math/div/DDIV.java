package com.jvm.instructions.math.div;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Divide double
public class DDIV extends NoOperandsInstruction {

    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Double v2 = stack.PopDouble();
        Double v1 = stack.PopDouble();
        Double result = v1 / v2;
        stack.PushDouble(result);
    }
}
