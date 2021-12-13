package com.jvm.instructions.math.div;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Divide float
public class FDIV extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Float v2 = stack.PopFloat();
        Float v1 = stack.PopFloat();
        Float result = v1 / v2;
        stack.PushFloat(result);
    }
}
