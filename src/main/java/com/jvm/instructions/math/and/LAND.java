package com.jvm.instructions.math.and;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;
// Boolean AND long
public class LAND extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Long v2 = stack.PopLong();
        Long v1 = stack.PopLong();
        Long result = v1 & v2;
        stack.PushLong(result);
    }
}
