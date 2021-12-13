package com.jvm.instructions.constants;

import com.jvm.instructions.base.NoOperandsInstruction;
import com.jvm.rtda.Frame;


public class ACONST_NULL extends NoOperandsInstruction {


    @Override
    public void Execute(Frame frame) {
        frame.OperandStack().PushRef(null);
    }
}