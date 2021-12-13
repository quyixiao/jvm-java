package com.jvm.instructions.conversions.f2x;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Convert float to int
public class F2I extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Float f = stack.PopFloat();
        Integer i = f.intValue();
        stack.PushInt(i);
    }


}
