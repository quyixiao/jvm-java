package com.jvm.instructions.math.mul;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Multiply int
public class IMUL extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int v2 = stack.PopInt();
        int v1 = stack.PopInt();
        int result = v1 * v2;
        stack.PushInt(result);
    }
}
