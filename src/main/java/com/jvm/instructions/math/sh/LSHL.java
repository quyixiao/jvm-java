package com.jvm.instructions.math.sh;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
import com.jvm.rtda.OperandStack;

public class LSHL extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        OperandStack stack = frame.OperandStack();
        int v2 = stack.PopInt();
        long v1 = stack.PopLong();
        int s = v2 & 0x3f;
        long result = v1 << s;
        stack.PushLong(result);
    }
}
