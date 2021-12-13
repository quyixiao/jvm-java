package com.jvm.instructions.math.and;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Boolean AND int
public class IAND extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Integer v2 = stack.PopInt();
        Integer v1 = stack.PopInt();
        int result = v1 & v2;
        stack.PushInt(result);
    }
}
