package com.jvm.instructions.constants.ldc;

import com.jvm.instructions.base.Index16Instruction;
import com.jvm.rtda.Frame;

public class LDC_W extends Index16Instruction {
    @Override
    public void Execute(Frame frame) {
        _ldc(frame, this.Index);
    }
}
