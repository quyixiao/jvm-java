package com.jvm.instructions.constants.consts;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
// Push float
public class FCONST_0 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        frame.OperandStack().PushFloat(0.0f);
    }
}
