package com.jvm.instructions.loads.fload;

import com.jvm.instructions.base.Index8Instruction;
import com.jvm.rtda.Frame;

public class FLOAD  extends Index8Instruction {
    @Override
    public void Execute(Frame frame) {
        _fload(frame, this.Index);
    }
}
