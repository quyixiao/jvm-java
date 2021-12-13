package com.jvm.instructions.math.add;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Add int
public class IADD extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        Integer v2 = stack.PopInt();
        Integer v1 = stack.PopInt();
        Integer result = v1 + v2;
        stack.PushInt(result);
    }
}
