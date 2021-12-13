package com.jvm.instructions.math.sh;


import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

// Arithmetic shift right int
public class ISHR extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int v2 = stack.PopInt();
        int v1 = stack.PopInt();
        int s = v2 & 0x1f;
        int result = v1 >> s;
        stack.PushInt(result);
    }
}
