package com.jvm.instructions.constants.consts;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class FCONST_1 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        frame.OperandStack().PushFloat(1.0f);
    }
}
