package com.jvm.instructions.math.or;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Boolean OR long
public class LOR extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        long v2 = stack.PopLong();
        long v1 = stack.PopLong();
        long result = v1 | v2;
        stack.PushLong(result);
    }
}
