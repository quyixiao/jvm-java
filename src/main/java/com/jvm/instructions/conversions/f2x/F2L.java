package com.jvm.instructions.conversions.f2x;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Convert float to long
public class F2L extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Float f = stack.PopFloat();
        Long l = f.longValue();
        stack.PushLong(l);
    }
}
