package com.jvm.instructions.conversions;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Convert int to char
public class I2C extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int i = stack.PopInt();
        char c = (char) i;
        stack.PushInt((int) c);
    }
}
