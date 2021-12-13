package com.jvm.instructions.constants.consts;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;
// Push int constant
public class ICONST_M1 extends NoOperandsInstruction {
    @Override
    public void Execute(Frame frame) {
        frame.OperandStack().PushInt(-1);
    }
}
