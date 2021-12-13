package com.jvm.instructions.math.add;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Add long
public class LADD extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Long v2 = stack.PopLong();
        Long v1 = stack.PopLong();
        Long result = v1 + v2;
        stack.PushLong(result);
    }
}
