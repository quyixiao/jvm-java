package com.jvm.instructions.conversions.i2x;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Convert int to short
public class I2S extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int i = stack.PopInt();
        short s = (short) i;
        stack.PushInt((int) s);
    }
}
