package com.jvm.instructions.conversions.i2x;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

public class I2B extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Integer i = stack.PopInt();
        byte b = i.byteValue();
        stack.PushInt((int) b);
    }
}
