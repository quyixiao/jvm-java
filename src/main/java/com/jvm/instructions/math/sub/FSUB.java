package com.jvm.instructions.math.sub;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Subtract float
public class FSUB extends NoOperandsInstruction {


    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        float v2 = stack.PopFloat();
        float v1 = stack.PopFloat();
        float result = v1 - v2;
        stack.PushFloat(result);

    }
}
