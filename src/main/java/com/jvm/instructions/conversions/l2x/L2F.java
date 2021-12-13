package com.jvm.instructions.conversions.l2x;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Convert long to float
public class L2F extends NoOperandsInstruction {


    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        long l = stack.PopLong();
        float f = (float) l;
        stack.PushFloat(f);
    }
}
