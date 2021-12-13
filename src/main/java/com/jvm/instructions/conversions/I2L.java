package com.jvm.instructions.conversions;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Convert int to long
public class I2L extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int i = stack.PopInt();
        long l = i;
        stack.PushLong(l);
    }
}
