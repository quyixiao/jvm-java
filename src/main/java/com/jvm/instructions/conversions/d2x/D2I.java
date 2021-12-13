package com.jvm.instructions.conversions.d2x;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Convert double to int
public class D2I extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Double d = stack.PopDouble();
        Integer i = d.intValue();
        stack.PushInt(i);
    }
}
