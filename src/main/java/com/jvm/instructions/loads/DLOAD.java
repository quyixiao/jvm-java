package com.jvm.instructions.loads;

import com.jvm.instructions.base.Index8Instruction;
import com.jvm.rtda.Frame;

public class DLOAD  extends Index8Instruction {
    @Override
    public void Execute(Frame frame) {
        _dload(frame, this.Index);
    }
}
