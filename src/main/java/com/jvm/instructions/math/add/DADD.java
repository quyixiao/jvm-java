package com.jvm.instructions.math.add;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Add double
public class DADD extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Double v1 = stack.PopDouble();
        Double v2 = stack.PopDouble();
        Double result = v1 + v2;
        stack.PushDouble(result);
    }
}
