package com.jvm.instructions.math.mul;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Multiply long
public class LMUL extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        long v2 = stack.PopLong();
        long v1 = stack.PopLong();
        long result = v1 * v2;
        stack.PushLong(result);
    }
}
