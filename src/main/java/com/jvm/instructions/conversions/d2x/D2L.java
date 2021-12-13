package com.jvm.instructions.conversions.d2x;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Convert double to long
public class D2L extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Double d = stack.PopDouble();
        Long l = d.longValue();
        stack.PushLong(l);
    }
}
