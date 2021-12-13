package com.jvm.instructions.math.neg;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Negate int
public class INEG extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int val = stack.PopInt();
        stack.PushInt(-val);
    }
}
