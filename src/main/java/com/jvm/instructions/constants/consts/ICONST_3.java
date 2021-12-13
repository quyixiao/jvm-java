package com.jvm.instructions.constants.consts;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;

public class ICONST_3 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        frame.OperandStack().PushInt(3);
    }
}
